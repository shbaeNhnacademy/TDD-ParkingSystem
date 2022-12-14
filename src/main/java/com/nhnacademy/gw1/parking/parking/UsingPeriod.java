package com.nhnacademy.gw1.parking.parking;

public class UsingPeriod {

    private final long elapsedSec;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int secs;
    private final int hourToMinute;


    public UsingPeriod(long elapsedSec) {
        this.elapsedSec = elapsedSec;
        this.days = (int) (elapsedSec / 24 / 60 / 60);
        this.hours = (int) (elapsedSec / 60 / 60 % 24);
        this.minutes = (int) ((elapsedSec) / 60 % 60);
        this.secs = (int) (elapsedSec % 60);
        this.hourToMinute = hours * 60;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecs() {
        return secs;
    }

    public int getHourToMinute() {
        return hourToMinute;
    }

    @Override
    public String toString() {
        return "UsingPeriod{" +
                "days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", secs=" + secs +
                ", hourToMinute=" + hourToMinute +
                '}';
    }
}
