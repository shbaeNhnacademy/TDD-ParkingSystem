package com.nhnacademy.gw1.parking.car;

import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;
import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;
import com.nhnacademy.gw1.parking.user.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    @DisplayName("차 번호가 4자리 숫자가 인 경우 정상  ")
    void carNumber_valid() {
        int carNum = 1234;
        User user = new User(new UserId(111L), new Money(10_000));
        assertThatCode(() -> new Car(carNum, user)).doesNotThrowAnyException();

    }


    @DisplayName("차 번호가 4자리 숫자가 아닐 경우 예외발생 ")
    @ParameterizedTest
    @ValueSource(ints = {123,12341234})
    void carNumber_invalid_thenInvalidCarNumberException(int candidate) {
        User user = new User(new UserId(111L), new Money(10_000));
        assertThatThrownBy(() -> new Car(candidate, user))
                .isInstanceOf(InvalidCarNumberException.class)
                .hasMessageContainingAll("Car number", "invalid", String.valueOf(candidate));
    }

}
