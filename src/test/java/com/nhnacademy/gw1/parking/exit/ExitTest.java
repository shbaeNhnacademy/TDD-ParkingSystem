package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.parking.ParkingLot;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    }

    @DisplayName("시간에 비례한 요금 추출 정상 작동 ")
    @ParameterizedTest
    @ValueSource(longs = {(30 * 60 + 1),(10 * 60),(60 * 60 + 1),(65 * 60)})
    void extractPrice_success(long candidate) {
        long price = exit.extractPrice(candidate);

//        long method = 0;
//        assertThat(price).isEqualTo(candidate * method);
    }

    @DisplayName("정상 작동 ")
    @ParameterizedTest
    @ValueSource(longs = {(30 * 60 + 1), (10 * 60), (60 * 60 + 1), (65 * 60), (24 * 60 * 60 + 1)})
    void pay_success(long candidate) {

        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 7, 30, 0);
        when(parkingSystem.checkTime(car, endDateTime)).thenReturn(candidate);

        exit.pay(car, parkingSystem, endDateTime);

    }


}
