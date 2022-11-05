package com.nhnacademy.gw1.parking.exit;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.parking.ParkingSystem;

import java.time.LocalDateTime;

public interface Exit {
    Car pay(Car car, ParkingSystem system, LocalDateTime endDateTime);

    default long extractPrice(long elapsedSec) {
        int days = (int) (elapsedSec / 24 / 60 / 60);
        int minutes = (int) ((elapsedSec - (days * 24 * 60 * 60)) / 60);
        int secs = (int) (elapsedSec - (minutes * 60));

        System.out.println("days = " + days);
        System.out.println("minutes = " + minutes);
        System.out.println("secs = " + secs);
        long price = 0L;
        int restMinutes = minutes - 30;//- (days * 24 * 60)
        if (days != 0) {
            price = 10_000L;
            while (restMinutes >= 0 || secs != 0) {
                if (price >= 10000) {
                    break;
                } else if (restMinutes <= 0 && secs != 0) {
                    price += 500L;
                    break;
                }
                price += 500L;
                restMinutes -= 10;
            }
        }else{
            price = 1_000L;
            while (restMinutes >= 0 || secs != 0) {
                if (price >= 10000) {
                    break;
                } else if (restMinutes <= 0 && secs != 0) {
                    price += 500L;
                    break;
                }
                price += 500L;
                restMinutes -= 10;
            }
        }
        System.out.println("## price = " + price);

//        if (minutes <= 30 && secs <= 0) {
//            price = 1000L;
//        }else {
//            int restMinutes = minutes - (days * 24 * 60) - 30;
//            System.out.println("restMinutes = " + restMinutes);
//            price = 1000L;
//            while (restMinutes >= 0) {
//                if (price >= 10000) {
//                    break;
//                } else if (restMinutes == 0 && secs != 0) {
//                    price += 500L;
//                    break;
//                }
//                price += 500L;
//                restMinutes -= 10;
//            }
//            price += 10_000L * days;
//        }
        return price;
    }

}
