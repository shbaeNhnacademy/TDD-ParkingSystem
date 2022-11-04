package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExitTest {

    //SUT
    Exit exit;

    //DOC
    ParkingSystem parkingSystem;

    Car car;
    @BeforeEach
    void setUp() {
        exit = new EastExit();

        parkingSystem = mock(ParkingSystem.class);

        LocalDateTime startDateTime = LocalDateTime.of(2022, 11, 5, 3, 30, 0);
        User user = new User(new UserId(111L), new Money(10_000), startDateTime);
        car = new Car(1234, user, CarGrade.COMPACT);

        aExit = new aEastExit(parkingSystem);
    }

    aExit aExit;
    @Test
    @DisplayName("추가 필요")
    void pay() {
        long elapsedSec = 60 * 60; // 1시간

        when(parkingSystem.checkTime(car, LocalDateTime.now())).thenReturn(elapsedSec);
        aExit.pay(car, LocalDateTime.now());

    }

}
