package com.nhnacademy.gw1.parking.enterance;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.exception.NotAllowedCarSizeException;

public interface Enterance {
    default Car scan(Car car){
        if (car.getGrade() == CarGrade.FULL_SIZE) {
            throw new NotAllowedCarSizeException(car);
        }
        return car;
    }
}
