package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;

import java.lang.annotation.Retention;
import java.time.Duration;
import java.time.LocalDateTime;

public class EastExit implements Exit{
    @Override
    public Car pay(Car car, ParkingSystem system, LocalDateTime endDateTime) {
        long elapsedSec = system.checkTime(car, endDateTime);

        extractPrice(elapsedSec);

        return null;
    }

}
