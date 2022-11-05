package com.nhnacademy.gw1.parking.user;


import java.time.LocalDateTime;

public class User {
    private final UserId userId;
    private Money amount;
    private final LocalDateTime startDateTime;

    public User(UserId userId, Money amount, LocalDateTime startDateTime) {
        this.userId = userId;
        this.amount = amount;
        this.startDateTime = startDateTime;
    }

    public UserId getUserId() {
        return userId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}
