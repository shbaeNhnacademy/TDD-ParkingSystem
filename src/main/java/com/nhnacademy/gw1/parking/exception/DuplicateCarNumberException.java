package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.car.Car;

public class DuplicateCarNumberException extends RuntimeException {
    public DuplicateCarNumberException(Car car) {
        super("Car number is duplicated: " + car.getNumber());
    }
}
