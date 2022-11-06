package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarGrade;
import com.nhnacademy.gw1.parking.exception.UserAmountNotEnoughException;
import com.nhnacademy.gw1.parking.user.Money;

public interface Exit {
    default Car pay(Car car, long price){
        long discountedPrice = verifyDiscount(car, price);
        Money amount = checkUserAmount(car, discountedPrice);
        car.getUser().setAmount(new Money(amount.getAmount() - discountedPrice));
        return car;
    }

    private static long verifyDiscount(Car car, long price) {
        if (car.getGrade().equals(CarGrade.COMPACT)) {
            price /= 2;
        } else if (car.getUser().getPayco().isMember()) {
            price = price - price * Math.round(car.getUser().getPayco().getDISCOUNT_RATE());
        }
        return price;
    }

    default Money checkUserAmount(Car car, long price) {
        Money amount = car.getUser().getAmount();
        if (price > amount.getAmount()) {
            throw new UserAmountNotEnoughException(amount);
        }
        return amount;
    }
}
