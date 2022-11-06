package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.exception.UserAmountNotEnoughException;
import com.nhnacademy.gw1.parking.user.Money;

public interface Exit {
    default Car pay(Car car, long price){
        if (car.getGrade().equals(CarGrade.COMPACT)) {
            price /= 2;
        }
        Money amount = checkUserAmount(car, price);
        car.getUser().setAmount(new Money(amount.getAmount() - price));
        return car;
    }

    default Money checkUserAmount(Car car, long price) {
        Money amount = car.getUser().getAmount();
        if (price > amount.getAmount()) {
            throw new UserAmountNotEnoughException(amount);
        }
        return amount;
    }
}
