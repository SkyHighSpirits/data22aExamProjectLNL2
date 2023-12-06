package com.example.data22aexamprojectlnl2.services;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


class EmailServiceTest
{


    @Test
    void emailSpamLimit()
    {

        LocalTime lastRequestTime = LocalTime.of(10, 10, 10);
        LocalTime now = LocalTime.of(10, 10, 11);
        long timepassed = lastRequestTime.until(now, ChronoUnit.SECONDS);


        //ratelimit set to one request pr 3 seconds globally
        long ratelimit = 3;

        boolean disAllowSending = timepassed < ratelimit;


        org.junit.jupiter.api.Assertions.assertTrue(disAllowSending);


    }
}