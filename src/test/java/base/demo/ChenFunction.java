package base.demo;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 * 被@FuncationInterface 注解修饰的 接口
 * 必须有一个抽象方法 并且只能有一个抽象 ,
 * 可以包含多个 default 的方法
 */
@FunctionalInterface
interface ChenFunction {

    void mothd();

    default String defatMethod() {
        return "这是一个defautl方法";
    }
}
