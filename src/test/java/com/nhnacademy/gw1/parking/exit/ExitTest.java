package com.nhnacademy.gw1.parking.exit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void pay() {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 12, 20, 9, 30, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 12, 21, 10, 0, 40);

        Duration duration = Duration.between(startDateTime, endDateTime);
        System.out.println("seconds : {}" + duration.getSeconds()/60/60);
    }
}
