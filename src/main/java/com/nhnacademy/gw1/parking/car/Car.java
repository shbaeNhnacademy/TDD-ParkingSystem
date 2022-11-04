package com.nhnacademy.gw1.parking.car;

import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;

public class Car {
    private final int number;

    public Car(int number) {
        checkCarNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void checkCarNumber(int number) {
        boolean matches = String.valueOf(number).matches("^[0-9]{4}$");
        if (!matches) {
            throw new InvalidCarNumberException(number);
        }
    }
}
