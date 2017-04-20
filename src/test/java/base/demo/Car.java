package base.demo;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 */
public class Car extends Person {

    public Car() {
    }

    public Car(String firstName, String lastName) {
        super(firstName, lastName);
    }

    private static Car crete(Supplier<Car> supplier) {
        return supplier.get();
    }

    private List<car1> car1s;

    public List<car1> getCar1s() {
        return car1s;
    }

    public void setCar1s(List<car1> car1s) {
        this.car1s = car1s;
    }

    public static class car1 {
        private String a;
        private Long b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Long getB() {
            return b;
        }

        public void setB(Long b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "car1{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    '}';
        }
    }
}

