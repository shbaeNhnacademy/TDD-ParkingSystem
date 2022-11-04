package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.enterance.Enterance;
import com.nhnacademy.gw1.parking.exception.DuplicateCarNumberException;
import com.nhnacademy.gw1.parking.exception.FullLotException;
import com.nhnacademy.gw1.parking.exit.Exit;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ParkingLotTest {

    //SUT
    ParkingLot parkingLot;

    //DOC
    Enterance enterance;
    Exit exit;

    Car car;

    User user;

    @BeforeEach
    void setUp() {
        enterance = mock(Enterance.class);
        exit = mock(Exit.class);

        parkingLot = new ParkingLot(enterance, exit);


        LocalDateTime startDateTime = LocalDateTime.of(2022, 11, 5, 3, 30, 0);
        user = new User(new UserId(111L), new Money(10_000), startDateTime);
        car = new Car(1234, user, CarGrade.COMPACT);
    }


    @Test
    @DisplayName("차량 진입 완료 - 스캔 및 주차 완료")
    void enter_success() {
        when(enterance.scan(car)).thenReturn(car);

        ParkingSpaceCode enter = parkingLot.enter(car);
        Map<ParkingSpaceCode, ParkingSpace> parkingSpaceMap = parkingLot.getParkingSpaceMap();

        assertThat(parkingSpaceMap.containsKey(enter)).isTrue();

        verify(enterance, times(1)).scan(any());
    }

    @Test
    @DisplayName("차량 스캔시 동일 차량 번호 확인시 예외발생 ")
    void enter_fail_sameCarNum_thenThrowDuplicateCarNumberException() {
        when(enterance.scan(car)).thenReturn(car);
        parkingLot.enter(car);
        assertThatThrownBy(() -> parkingLot.enter(car))
                .isInstanceOf(DuplicateCarNumberException.class)
                .hasMessageContainingAll("Car number", "duplicated", car.getNumber().toString());

    }

    @Test
    @DisplayName("차량 만차시 예외 발생 ")
    void enter_fail_fullParkedCar_thenThrowFullParkingSpaceException() {
        for (int i = 0; i < ParkingSpaceCode.values().length; i++) {
            Car tempCar = new Car((1200 + i), user, CarGrade.COMPACT);
            when(enterance.scan(tempCar)).thenReturn(tempCar);
            parkingLot.enter(tempCar);
        }

        when(enterance.scan(car)).thenReturn(car);
        assertThatThrownBy(() -> parkingLot.enter(car))
                .isInstanceOf(FullLotException.class)
                .hasMessageContainingAll("Full lot", car.getNumber().toString());

        verify(enterance, times(ParkingSpaceCode.values().length + 1)).scan(any());
    }

    @Test
    void exit() {
    }
}
