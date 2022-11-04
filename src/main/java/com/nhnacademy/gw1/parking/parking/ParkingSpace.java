package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;

public class ParkingSpace {
    private final String code;
    private final Car car;

    public ParkingSpace(String code, Car car) {
        this.code = code;
        this.car = car;
    }

    public String getCode() {
        return code;
    }

    public Car getCar() {
        return car;
    }
}
