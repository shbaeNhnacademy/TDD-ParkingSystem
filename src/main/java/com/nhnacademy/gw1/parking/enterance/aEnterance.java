package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;

public abstract class aEnterance {
    private final ParkingSystem parkingSystem;

    public aEnterance(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }

    public Car scan(Car car) {
        return car;
    }
}
