package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

public class Spaces {
    private final List<ParkingSpace> spaces;

    public Spaces() {
        spaces = new ArrayList<>();
    }

    public void getSpace(ParkingSpaceCode code, Car car) {
        for (ParkingSpace space : spaces) {
//            if(car.get)
        }
    }
}
