package com.nhnacademy.gw1.parking.user;

public class Money {
    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + "";
    }
}
