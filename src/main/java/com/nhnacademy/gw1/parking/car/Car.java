package com.nhnacademy.gw1.parking.car;

import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;
import com.nhnacademy.gw1.parking.user.User;

public class Car {
    private final Integer number;
    private final User user;
    private final CarGrade grade;

    public Car(Integer number, User user, CarGrade grade) {
        checkCarNumber(number);
        this.number = number;
        this.user = user;
        this.grade = grade;
    }

    public Integer getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public CarGrade getGrade() {
        return grade;
    }

    private void checkCarNumber(Integer number) {
        boolean matches = String.valueOf(number).matches("^[0-9]{4}$");
        if (!matches) {
            throw new InvalidCarNumberException(number);
        }
    }
}
