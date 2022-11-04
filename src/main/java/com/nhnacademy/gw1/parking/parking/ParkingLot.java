package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.enterance.Enterance;
import com.nhnacademy.gw1.parking.exception.DuplicateCarNumberException;
import com.nhnacademy.gw1.parking.exception.FullLotException;
import com.nhnacademy.gw1.parking.exit.Exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private final Map<ParkingSpaceCode, ParkingSpace> parkingSpaceMap;
    private final Enterance enterance;
    private final Exit exit;

    private static final int MAX_SPACE_SIZE = ParkingSpaceCode.values().length;


    public ParkingLot(Enterance enterance, Exit exit) {
        this.enterance = enterance;
        this.exit = exit;

        parkingSpaceMap = new ConcurrentHashMap<>();
    }


    public ParkingSpaceCode enter(Car car) {
        Car scanCar = this.enterance.scan(car);
        checkDuplicateCarNum(scanCar);
        return park(scanCar);
    }

    private void checkDuplicateCarNum(Car car) {
        for (ParkingSpaceCode key : parkingSpaceMap.keySet()) {
            Integer number = parkingSpaceMap.get(key).getCar().getNumber();
            if (number.equals(car.getNumber())) {
                throw new DuplicateCarNumberException(car);
            }
        }
    }

    public Car exit(Car car) {
        return null;
    }

    public Map<ParkingSpaceCode, ParkingSpace> getParkingSpaceMap() {
        return parkingSpaceMap;
    }

    public ParkingSpaceCode park(Car car) {
        ParkingSpaceCode code = verifyParkingSpace();
        if (!Objects.isNull(code)) {
            parkingSpaceMap.put(code, new ParkingSpace(code, car));
            return code;
        }
        throw new FullLotException(car);
    }

    private ParkingSpaceCode verifyParkingSpace() {
        if (parkingSpaceMap.size() > MAX_SPACE_SIZE) {
            return null;
        } else if (parkingSpaceMap.size() == 0) {
            return ParkingSpaceCode.A_01;
        }

        for (ParkingSpaceCode value : ParkingSpaceCode.values()) {
            if (!parkingSpaceMap.containsKey(value)) {
                return value;
            }
        }
        return null;
    }
}
