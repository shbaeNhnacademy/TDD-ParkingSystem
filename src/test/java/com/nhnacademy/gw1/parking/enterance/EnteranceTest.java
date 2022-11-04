package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class EnteranceTest {

    Enterance enterance;

    @BeforeEach
    void setUp() {
        //TODO Composite 사용해보기
        enterance = new EastEnterance();
    }

    @Test
    void scan() {
        int carNum = 1234;
        Car car = new Car(carNum);

        Car scan = enterance.scan(car);


    }
}
