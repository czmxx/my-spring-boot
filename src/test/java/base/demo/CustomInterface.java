package base.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 * 自定义接口
 */
@Target(ElementType.METHOD)
public @interface CustomInterface {

    String name();

    String value();

}
