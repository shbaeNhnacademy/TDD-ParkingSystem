package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.car.Car;

public class NotAllowedCarSizeException extends RuntimeException{
    public NotAllowedCarSizeException(Car car) {
        super(car.getGrade() + " is not allowed car size.");
    }
}
