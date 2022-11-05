package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;

public interface Exit {
    Car pay(Car car, long price);

}
