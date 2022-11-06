package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.enterance.Enterance;
import com.nhnacademy.gw1.parking.exception.DuplicateCarNumberException;
import com.nhnacademy.gw1.parking.exception.FullLotException;
import com.nhnacademy.gw1.parking.exception.UnregisteredUserException;
import com.nhnacademy.gw1.parking.exit.Exit;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ParkingLotTest {

    //SUT
    ParkingLot parkingLot;

    //DOC
    ParkingSystem parkingSystem ;
    Enterance enterance;
    Exit exit;

    Car car;

    User user;

    @BeforeEach
    void setUp() {
        parkingSystem = mock(ParkingSystem.class);
        enterance = mock(Enterance.class);
        exit = mock(Exit.class);

        parkingLot = new ParkingLot(parkingSystem, enterance, exit);

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
    @DisplayName("차량 출차 가능 상태")
    void exit_success() {
        long sec = 3601L;
        long price = 3000L;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 7, 30, 0);
        when(parkingSystem.checkTime(car, endDateTime)).thenReturn(sec);
        when(parkingSystem.extractPrice(sec)).thenReturn(price);
        when(parkingSystem.getUsers()).thenReturn(new ArrayList<>(List.of(car.getUser())));
        when(exit.pay(car, price)).thenReturn(car);

        Car exitedCar = parkingLot.exit(car, endDateTime);

        assertThat(exitedCar).isEqualTo(car);
        verify(parkingSystem, times(1)).checkTime(any(), any());
    }

    @Test
    @DisplayName("exit 정상 작동 - 사용자 삭제 확인 ")
    void exit_success_userRemove() {
        long sec = 3601L;
        long price = 3000L;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 7, 30, 0);
        when(parkingSystem.checkTime(car, endDateTime)).thenReturn(sec);
        when(parkingSystem.extractPrice(sec)).thenReturn(price);
        when(parkingSystem.getUsers()).thenReturn(new ArrayList<>(List.of(car.getUser())));
        when(exit.pay(car, price)).thenReturn(car);

        Car exitedCar = parkingLot.exit(car, endDateTime);

        assertThat(parkingSystem.getUsers().contains(exitedCar)).isFalse();
    }

    @Test
    @DisplayName("등록되지않은 사용자인 경우 예외발생")
    void exit_userNotFound_thenThrowUnregisteredUserException() {
        long sec = 3601L;
        long price = 3000L;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 7, 30, 0);
        when(parkingSystem.checkTime(car, endDateTime)).thenReturn(sec);
        when(parkingSystem.extractPrice(sec)).thenReturn(price);
        when(parkingSystem.getUsers()).thenReturn(new ArrayList<>(List.of(new User(new UserId(11L), new Money(10_000), LocalDateTime.now()))));
        when(exit.pay(car, price)).thenReturn(car);

        assertThatThrownBy(() -> parkingLot.exit(car, endDateTime))
                .isInstanceOf(UnregisteredUserException.class)
                .hasMessageContainingAll("unregistered", car.getUser().getUserId().toString());
    }

    @Test
    @DisplayName("exit 정상 작동 - 주차 자리 점유 삭제 확인 ")
    void exit_success_spaceRemove() {
        long sec = 3601L;
        long price = 3000L;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 11, 5, 7, 30, 0);
        when(parkingSystem.checkTime(car, endDateTime)).thenReturn(sec);
        when(parkingSystem.extractPrice(sec)).thenReturn(price);
        when(parkingSystem.getUsers()).thenReturn(new ArrayList<>(List.of(car.getUser())));
        when(exit.pay(car, price)).thenReturn(car);

        parkingLot.getParkingSpaceMap().put(ParkingSpaceCode.A_02, new ParkingSpace(ParkingSpaceCode.A_02, car));

        int size = parkingLot.getParkingSpaceMap().size();
        parkingLot.exit(car, endDateTime);

        assertThat(parkingLot.getParkingSpaceMap().size()).isEqualTo(size - 1); //삭제 확인
    }

}
