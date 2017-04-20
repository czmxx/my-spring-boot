package base.demo;

import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 */
public class ChenFunctionImplTest {

    public static void main(String[] args) {


        Car.car1 car1 = new Car.car1();
        car1.setA("1");
        car1.setB(2L);

        Car car = new Car();
        //car.setCar1s(Collections.singletonList(car1));

        System.out.print(car);

        LocalDateTime time = LocalDateTime.now();
        List<T> t  =new ArrayList<>();
//        t.stream().allMatch()



//        PersonFactory<Car> bZ = Car::new;


    }


}