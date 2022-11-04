package com.nhnacademy.gw1.parking.exception;

public class InvalidCarNumberException extends RuntimeException{
    public InvalidCarNumberException(int carNumber) {
        super("Car number is invalid: " + carNumber);
    }
}
