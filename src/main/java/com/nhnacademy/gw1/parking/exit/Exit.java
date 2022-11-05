package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;

import java.time.LocalDateTime;

public interface Exit {
    Car pay(Car car, ParkingSystem system, LocalDateTime endDateTime);

}
