package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.user.User;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    private final ParkingLot parkingLot;
    private List<User> users;

    public ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        users = new ArrayList<>();
    }
}
