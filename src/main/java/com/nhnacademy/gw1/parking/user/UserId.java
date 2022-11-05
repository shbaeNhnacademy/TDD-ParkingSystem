package com.nhnacademy.gw1.parking.user;

public class UserId {
    private final long userId;

    public UserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId + "";
    }
}
