package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ParkingSystemTest {
    //SUT
    ParkingSystem parkingSystem;

    //DOC
    ParkingLot parkingLot;

    Car car;
    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
        parkingSystem = new ParkingSystem(parkingLot);

        LocalDateTime startDateTime = LocalDateTime.of(2022, 11, 5, 3, 30, 0);
        User user = new User(new UserId(111L), new Money(10_000), startDateTime);
        car = new Car(1234, user, CarGrade.COMPACT);
    }

    @DisplayName("주차 시간 계산 검증 완료")
    @ParameterizedTest
    @ValueSource(ints = {1,2,6})
    void checkTime_success_hours(int candidate) {
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 3 + candidate, 30, 0);
        long elapsedSec = parkingSystem.checkTime(car, endDateTime);

        assertThat(elapsedSec).isEqualTo(60 * 60 * (long) candidate);
    }

    @DisplayName("주차 기간 계산 검증 완료")
    @ParameterizedTest
    @ValueSource(ints = {1,2,6})
    void checkTime_success_days(int candidate) {
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5 + candidate, 3, 30, 0);
        long elapsedSec = parkingSystem.checkTime(car, endDateTime);

        assertThat(elapsedSec).isEqualTo(60 * 60 * 24 * (long) candidate);
    }

    @ParameterizedTest
    @DisplayName("시간에 비례한 요금 추출 정상 작동 ")
    @CsvFileSource(resources = "/refactoring_extractPriceTest.csv", numLinesToSkip = 1)
    void extractPrice_success(long elapsedTime, long realPrice) {
        long price = parkingSystem.extractPrice(elapsedTime);

        assertThat(price).isEqualTo(realPrice);
    }
}
