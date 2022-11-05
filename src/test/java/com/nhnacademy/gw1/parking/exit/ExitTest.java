package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.exception.UnregisteredUserException;
import com.nhnacademy.gw1.parking.exception.UserAmountNotEnoughException;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;
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
import static org.mockito.Mockito.*;

class ExitTest {

    //SUT
    Exit exit;

    //DOC
    ParkingSystem parkingSystem;
    User user;
    Car car;
    @BeforeEach
    void setUp() {
        exit = new EastExit();

        parkingSystem = mock(ParkingSystem.class);

        LocalDateTime startDateTime = LocalDateTime.of(2022, 11, 5, 3, 30, 0);
        user = new User(new UserId(111L), new Money(10_000), startDateTime);
        car = new Car(1234, user, CarGrade.COMPACT);

    }

    @Test
    @DisplayName("pay 정상 작동 ")
    void pay_success() {
        long price = 3000L;
        Money userAmount = user.getAmount();
        Car paidCar = exit.pay(car, price);

        assertThat(paidCar.getUser().getAmount().getAmount()).isEqualTo((userAmount.getAmount() - price));
    }

    @Test
    @DisplayName("고객이 가지고있는 돈 보다 요금이 더 클 때, 예외발생")
    void pay_priceMoreThanAmount_thenThrowUserAmountDeficitException() {
        long price = 20_000L;

        assertThatThrownBy(() -> exit.pay(car, price))
                .isInstanceOf(UserAmountNotEnoughException.class)
                .hasMessageContainingAll("not have enough money", car.getUser().getAmount().toString());
    }

    @Test
    @DisplayName("새로 추가한 출구 westExit - pay 정상 작동 ")
    void pay_westExit_success() {
        exit = new WestExit();
        long price = 3000L;
        Money userAmount = user.getAmount();
        Car paidCar = exit.pay(car, price);

        assertThat(paidCar.getUser().getAmount().getAmount()).isEqualTo((userAmount.getAmount() - price));
    }

    @Test
    @DisplayName("새로 추가한 출구 westExit - 고객이 가지고있는 돈 보다 요금이 더 클 때, 예외발생")
    void pay_westExit_priceMoreThanAmount_thenThrowUserAmountDeficitException() {
        exit = new WestExit();
        long price = 20_000L;

        assertThatThrownBy(() -> exit.pay(car, price))
                .isInstanceOf(UserAmountNotEnoughException.class)
                .hasMessageContainingAll("not have enough money", car.getUser().getAmount().toString());
    }
}
