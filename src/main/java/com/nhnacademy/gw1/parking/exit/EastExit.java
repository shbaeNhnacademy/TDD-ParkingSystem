package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.exception.UserAmountNotEnoughException;
import com.nhnacademy.gw1.parking.user.Money;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EastExit implements Exit{
    @Override
    public Car pay(Car car, long price) {
        Money amount = checkUserAmount(car, price);
        car.getUser().setAmount(new Money(amount.getAmount() - price));
        return car;
    }

    private static Money checkUserAmount(Car car, long price) {
        Money amount = car.getUser().getAmount();
        if (price > amount.getAmount()) {
            throw new UserAmountNotEnoughException(amount);
        }
        return amount;
    }
}
