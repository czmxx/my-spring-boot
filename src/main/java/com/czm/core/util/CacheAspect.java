package com.czm.core.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CHENZHANMEI on 2017/3/11.
 * 用于对标签进行解析
 */
@Component
@Aspect
public class CacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.czm.core.util.EnableRedisCache)")
    public Object enableCache(ProceedingJoinPoint point) {

        Object result;
        Method method = getMethod(point);
        EnableRedisCache cacheable = method.getAnnotation(EnableRedisCache.class);

        String key = parseKey(cacheable.key(), method, point.getArgs());
        //在内存缓存中判断是否存在
        CacheEntity cache = CacheHandler.getCache(key);
        if (cache != null) return cache.getContent();
        //使用redis 的hash进行存取，易于管理
        result = redisTemplate.opsForValue().get(key);

        if (result == null) {
            try {
                result = point.proceed();
                Assert.notNull(key);
                redisTemplate.opsForValue().set(key, result);
                redisTemplate.expire(key, cacheable.expireTime(), TimeUnit.SECONDS);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        CacheHandler.addCache(key, new CacheEntity(key, result));
        return result;
    }

    private String parseKey(String key, Method method, Object[] args) {
        Pattern pattern = Pattern.compile("(?<=\\{)[^\\}]+");
        Matcher matcher = pattern.matcher(key);
        while (matcher.find()) {
            key = key.replace("{" + matcher.group() + "}", getOneValue(matcher.group(), method, args));
        }

        return key;
    }

    /**
     * 获取被拦截方法对象
     * <p/>
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     */
    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method;
    }

    private String getOneValue(String filedKey, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(filedKey).getValue(context, String.class);
    }

}
