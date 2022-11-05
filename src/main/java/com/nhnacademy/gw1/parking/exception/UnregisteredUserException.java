package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.user.User;

public class UnregisteredUserException extends RuntimeException{
    public UnregisteredUserException(User user) {
        super("User is unregistered: " + user.getUserId());
    }
}
