package com.nhnacademy.gw1.parking.user;

import java.math.BigDecimal;

public class User {
    private final Long userId;
    private final BigDecimal amount;

    public User(Long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
