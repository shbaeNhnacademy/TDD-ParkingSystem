package com.nhnacademy.gw1.parking.car;

import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;
import com.nhnacademy.gw1.parking.user.User;

public class Car {
    private final int number;
    private final User user;

    public Car(int number, User user) {
        checkCarNumber(number);
        this.number = number;
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    private void checkCarNumber(int number) {
        boolean matches = String.valueOf(number).matches("^[0-9]{4}$");
        if (!matches) {
            throw new InvalidCarNumberException(number);
        }
    }
}
