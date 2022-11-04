package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDateTime;

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
//        Car car = new Car(carNum);
//
//        Car scan = enterance.scan(car);

        LocalDateTime startDateTime = LocalDateTime.of(2020, 12, 20, 9, 30, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 12, 21, 10, 0, 40);

        Duration duration = Duration.between(startDateTime, endDateTime);
        System.out.println("seconds : {}" + duration.getSeconds()/60/60);

    }
}
