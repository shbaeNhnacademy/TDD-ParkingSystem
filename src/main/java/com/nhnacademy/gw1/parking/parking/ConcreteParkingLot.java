package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;

import java.util.List;

public class ConcreteParkingLot implements ParkingLot {
    private List<ConcreteParkingLot> spaces;

    @Override
    public Car enter() {
        return null;
    }

    @Override
    public Car exit() {
        return null;
    }
}
