package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.user.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    private final ParkingLot parkingLot;
    private final List<User> users;

    public ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        users = new ArrayList<>();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<User> getUsers() {
        return users;
    }

    public long checkTime(Car car, LocalDateTime endDateTime) {
        Duration between = Duration.between(car.getUser().getStartDateTime(), endDateTime);
        return between.getSeconds();
    }
}
