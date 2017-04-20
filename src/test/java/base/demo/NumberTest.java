package base.demo;

/**
 * Created by CHENZHANMEI on 2017/3/14.
 * Integer i  =1000 不能与Integer 1000
 */
public class NumberTest {


    public static void main(String[] args) {

        Integer a1 = 100;
        Integer b1 = 100;
        Integer a2 = 1000;
        Integer b2 = 1000;
/**Integer  -128 ---- 127可以使用== 进行比较 .
 *  大于不能使用
 *
 */
        System.out.println(a1 == b1);
        System.out.println(a2.equals(b2));

        Long l1 = 100L;
        Long l2 = 100L;

        Long o1 = 1000L;
        Long o2 = 1000L;

        System.out.println("Long 值进行比较" + (l1 == l2));
        System.out.println("Long 值进行比较" + (o1 == o2));

        /**
         *Double不能使用 == 进行比较只能使用 equals compareTo
         */
        Double d = 1.0;
        Double d1 = 1.0;
        Double d2 = 10000.0;
        Double d3 = 10000.0;
        System.out.println(d == d1);
        System.out.println(d2 == d3);
        System.out.println(d.compareTo(d1));
        System.out.println(d2.compareTo(d3));
        System.out.println(d.equals(d1));
    }


}
