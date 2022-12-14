package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.user.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    private final ParkingLot parkingLot;
    private final List<User> users;

    public ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        users = new ArrayList<>();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<User> getUsers() {
        return users;
    }


    public long checkTime(Car car, LocalDateTime endDateTime) {
        Duration between = Duration.between(car.getUser().getStartDateTime(), endDateTime);
        return between.getSeconds();
    }

    public long extractPrice(long elapsedSec) {
        UsingPeriod usingPeriod = new UsingPeriod(elapsedSec);
        int totalMinutes = usingPeriod.getMinutes() + usingPeriod.getHourToMinute();

        return calculateByPricePolicy(usingPeriod.getDays(), totalMinutes, usingPeriod.getSecs());
    }

    private long calculateByPricePolicy(int days, int minutes, int secs) {
        boolean canBeFree = (PricePolicy.FREE.getTerm() > minutes) || (PricePolicy.FREE.getTerm() == minutes && secs == 0);
        if ((days == 0) && canBeFree) {
            return PricePolicy.FREE.getPriceWon();
        }
        int restMinutes = minutes - PricePolicy.DEFAULT.getTerm() - PricePolicy.FREE.getTerm(); //추가 부과 시간
        return verifyDaysOrMinutes(days, secs, restMinutes);
    }

    private long verifyDaysOrMinutes(int days, int secs, int restMinutes) {
        long price = 0L;
        if (days != 0) {
            price = getPrice(secs, price, restMinutes);
            price += PricePolicy.DAY.getPriceWon() * days; //일 부과 요금
        } else {
            price = PricePolicy.DEFAULT.getPriceWon(); //기본 부과 요금
            price = getPrice(secs, price, restMinutes);
        }
        return price;
    }

    private long getPrice(int secs, long price, int restMinutes) {
        if (PricePolicy.FREE.getTerm() != 0 && restMinutes < 0) {
            return price;
        }
        while (restMinutes >= 0 || secs != 0) {
            if (price >= PricePolicy.DAY.getPriceWon()) { //일 부과 요금
                break;
            } else if (restMinutes <= 0 && secs != 0) {
                price += PricePolicy.ADDITIONAL.getPriceWon(); //추가 부과 요금
                break;
            }
            price += PricePolicy.ADDITIONAL.getPriceWon(); //추가 부과 요금
            restMinutes -= PricePolicy.ADDITIONAL.getTerm(); // 추가 부과 기준 시간
        }
        return price;
    }
}
