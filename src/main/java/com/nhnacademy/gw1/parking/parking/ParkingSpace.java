package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;

public class ParkingSpace {
    private final ParkingSpaceCode code;
    private final Car car;

    public ParkingSpace(ParkingSpaceCode code, Car car) {
        this.code = code;
        this.car = car;
    }

    public ParkingSpaceCode getCode() {
        return code;
    }

    public Car getCar() {
        return car;
    }
}
