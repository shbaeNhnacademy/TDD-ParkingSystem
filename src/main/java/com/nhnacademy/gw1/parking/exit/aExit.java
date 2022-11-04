package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;

import java.time.LocalDateTime;

public abstract class aExit {
    private final ParkingSystem parkingSystem;

    public aExit(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }

    public Car pay(Car car, LocalDateTime endDateTime) {
        long elapsedSec = parkingSystem.checkTime(car, endDateTime);
        System.out.println("elapsedSec = " + elapsedSec);
//        int days = (int) (elapsedSec / 24 / 60 / 60);
//        int hours = (int) ((elapsedSec )/ 60 / 60);
//        int minutes = (int) (elapsedSec / 60);
//        int secs = (int) (elapsedSec / 24 / 60 / 60);
        int minutes = (int) (elapsedSec / 60);
        int secs = (int) ((elapsedSec - minutes * 60) / 60);
        System.out.println("secs = " + secs);
        System.out.println("minutes = " + minutes);
        return car;
    }
}
