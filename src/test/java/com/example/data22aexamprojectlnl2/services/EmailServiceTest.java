package com.example.data22aexamprojectlnl2.services;

import org.junit.jupiter.api.Test;
import org.mockito.internal.junit.JUnitRule;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;


class EmailServiceTest {


    @Test
    void emailSpamLimit() {

        LocalTime lastRequestTime = LocalTime.of(10,10,10);
        LocalTime now = LocalTime.of(10,10,11);
        long timepassed = lastRequestTime.until(now, ChronoUnit.SECONDS);


        //ratelimit set to one request pr 3 seconds globally
        long ratelimit = 3;

        boolean allowSending = timepassed < ratelimit;


        org.junit.jupiter.api.Assertions.assertFalse(allowSending);


    }
}