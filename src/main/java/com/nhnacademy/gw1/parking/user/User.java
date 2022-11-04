package com.nhnacademy.gw1.parking.user;


public class User {
    private final UserId userId;
    private final Money amount;

    public User(UserId userId, Money amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public UserId getUserId() {
        return userId;
    }

    public Money getAmount() {
        return amount;
    }
}
