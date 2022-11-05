package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.user.Money;
import com.nhnacademy.gw1.parking.user.User;

public class UserAmountNotEnoughException extends RuntimeException {
    public UserAmountNotEnoughException(Money amount) {
        super("User does not have enough money: " + amount);
    }
}
