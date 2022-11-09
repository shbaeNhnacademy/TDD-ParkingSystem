package com.nhnacademy.gw1.parking.parking;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.enterance.Enterance;
import com.nhnacademy.gw1.parking.exception.DuplicateCarNumberException;
import com.nhnacademy.gw1.parking.exception.FullLotException;
import com.nhnacademy.gw1.parking.exception.UnregisteredUserException;
import com.nhnacademy.gw1.parking.exit.Exit;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private final Map<ParkingSpaceCode, ParkingSpace> parkingSpaceMap;
    private final ParkingSystem parkingSystem;
    private final Enterance enterance;
    private final Exit exit;
    private static final int MAX_SPACE_SIZE = ParkingSpaceCode.values().length;

    public ParkingLot(ParkingSystem parkingSystem, Enterance enterance, Exit exit) {
        this.parkingSystem = parkingSystem;
        this.enterance = enterance;
        this.exit = exit;

        parkingSpaceMap = new ConcurrentHashMap<>();
    }

    public ParkingSpaceCode enter(Car car) {
        Car scanCar = this.enterance.scan(car);
        checkDuplicatedCarNum(scanCar);
        ParkingSpaceCode park = park(scanCar);
        parkingSystem.getUsers().add(car.getUser());
        return park;
    }

    private void checkDuplicatedCarNum(Car car) {
        for (ParkingSpaceCode key : parkingSpaceMap.keySet()) {
            Integer number = parkingSpaceMap.get(key).getCar().getNumber();
            if (number.equals(car.getNumber())) {
                throw new DuplicateCarNumberException(car);
            }
        }
    }

    public Car exit(Car car, LocalDateTime endDateTime) {
        checkRegisteredUser(car, this.parkingSystem);
        long elapsedSec = this.parkingSystem.checkTime(car, endDateTime);
        long price = this.parkingSystem.extractPrice(elapsedSec);
        Car paidCar = this.exit.pay(car, price);
        removeUserAndSpace(car, paidCar);
        return paidCar;
    }

    private void removeUserAndSpace(Car car, Car paidCar) {
        this.parkingSystem.getUsers().remove(car.getUser());
        for (Map.Entry<ParkingSpaceCode, ParkingSpace> parkingSpaceCodeParkingSpaceEntry : parkingSpaceMap.entrySet()) {
            ParkingSpaceCode key = parkingSpaceCodeParkingSpaceEntry.getKey();
            ParkingSpace value = parkingSpaceCodeParkingSpaceEntry.getValue();
            if (value.getCar().equals(paidCar)) {
                parkingSpaceMap.remove(key);
                break;
            }
        }
    }

    private void checkRegisteredUser(Car car, ParkingSystem system) {
        if (!system.getUsers().contains(car.getUser())) {
            throw new UnregisteredUserException(car.getUser());
        }
    }

    private ParkingSpaceCode park(Car car) {
        ParkingSpaceCode code = verifyParkingSpace(car);
        parkingSpaceMap.put(code, new ParkingSpace(code, car));
        return code;
    }

    private ParkingSpaceCode verifyParkingSpace(Car car) {
        if (parkingSpaceMap.size() == 0) {
            return ParkingSpaceCode.A_01;
        }

        if (parkingSpaceMap.size() > MAX_SPACE_SIZE) {
           throw new FullLotException(car);
        }

        for (ParkingSpaceCode value : ParkingSpaceCode.values()) {
            if (!parkingSpaceMap.containsKey(value)) {
                return value;
            }
        }
        throw new FullLotException(car);
    }

    public Map<ParkingSpaceCode, ParkingSpace> getParkingSpaceMap() {
        return parkingSpaceMap;
    }
}
