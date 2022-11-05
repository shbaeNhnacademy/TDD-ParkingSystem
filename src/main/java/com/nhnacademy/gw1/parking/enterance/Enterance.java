package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;

public interface Enterance {
    default Car scan(Car car){
        return car;
    }
}
