package com.example.data22aexamprojectlnl2.services;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashingServiceTest {

    @Test
    void doHashingTest() throws NoSuchAlgorithmException {
        // Create an instance of your class (the one containing the doHashing method)
        PasswordHashingService hashingService = new PasswordHashingService();

        // Test input password
        String password = "yourPassword";

        // Expected hash value for the given input password
        String expectedHash = "ff85305ab86ceff0f59877358928d81d"; // Replace with the actual expected hash value

        // Perform hashing
        String actualHash = hashingService.doHashing(password);

        // Assert that the actual hash matches the expected hash
        assertEquals(expectedHash, actualHash);
    }
}