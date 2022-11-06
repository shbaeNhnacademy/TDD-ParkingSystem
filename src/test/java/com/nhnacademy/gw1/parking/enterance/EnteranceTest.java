package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.exception.NotAllowedCarSizeException;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnteranceTest {
    Enterance enterance;

    User user;
    @BeforeEach
    void setUp() {
        //TODO Composite 사용해보기
        enterance = new EastEnterance();

        LocalDateTime startDateTime = LocalDateTime.of(2022, 11, 5, 3, 30, 0);
        user = new User(new UserId(111L), new Money(10_000),startDateTime);
    }

    @Test
    @DisplayName("입구에서 차량 확인 - byPass")
    void scan_success_byPass() {
        int carNum = 1234;
        Car car = new Car(carNum, user, CarGrade.COMPACT);

        Car scanCar = enterance.scan(car);

        assertThat(scanCar).isEqualTo(car);
    }


    @Test
    @DisplayName("새로 추가한 west 입구에서 차량 확인 - byPass")
    void scan_westEnterance_success_byPass() {
        enterance = new WestEnterance();
        int carNum = 1234;
        Car car = new Car(carNum, user, CarGrade.COMPACT);

        Car scanCar = enterance.scan(car);

        assertThat(scanCar).isEqualTo(car);
    }

    @Test
    @DisplayName("대형차는 주차 할 수 없음")
    void scan_fullSizeCar_thenThrowNotAllowedCarSizeException() {
        enterance = new WestEnterance();
        int carNum = 1234;
        Car car = new Car(carNum, user, CarGrade.FULL_SIZE);
        assertThatThrownBy(() -> enterance.scan(car))
                .isInstanceOf(NotAllowedCarSizeException.class);
    }
}
