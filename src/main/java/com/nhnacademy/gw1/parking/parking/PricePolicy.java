package com.nhnacademy.gw1.parking.parking;

public enum PricePolicy {
    FREE(30, 0L),
    DEFAULT(30, 1000L),
    ADDITIONAL(10, 500L),
    DAY(24 * 60, 15_000L),;



    private final int term;
    private final long priceWon;

    PricePolicy(int term, long priceWon) {
        this.term = term;
        this.priceWon = priceWon;
    }

    public long getPriceWon() {
        return priceWon;
    }

    public int getTerm() {
        return term;
    }
}
