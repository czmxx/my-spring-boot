package base.demo;

import java.lang.reflect.Method;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 */
public class AnnotationParsing {

    public static void main(String[] args) throws ClassNotFoundException {


        Method[] methods = AnnotationParsing.class
                .getClassLoader()
                .loadClass("base.demo.AnnotationParsing")
                .getMethods();


        for (Method method : methods) {
            System.out.println(method);





        }


    }


}
