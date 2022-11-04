package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.car.Car;

public class FullLotException extends RuntimeException{
    public FullLotException(Car car) {
        super("Full lot. " + car.getNumber() + " can not park");
    }
}
