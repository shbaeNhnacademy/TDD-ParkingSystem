package com.nhnacademy.gw1.parking.user;

public class Payco {
    private boolean isMember;
    private final double DISCOUNT_RATE = 0.1;

    public Payco(boolean isMember) {
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public double getDISCOUNT_RATE() {
        return DISCOUNT_RATE;
    }
}
