package com.example.data22aexamprojectlnl2.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.*;
import java.time.temporal.ChronoUnit;

@Service
public class EmailService {

    LocalTime lastRequestTime;
    private long timepassed = 0;

    //ratelimit set to one request pr 3 seconds globally
    private long ratelimit = 3;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {

        LocalTime now = LocalTime.now();

        if(lastRequestTime != null)
        {
            timepassed = lastRequestTime.until(now, ChronoUnit.SECONDS);
        }

        if(timepassed > ratelimit)
        {
            try {
                // Validate input parameters
                if (to == null || to.isEmpty()) {
                    throw new IllegalArgumentException("Recipient email address is required.");
                }
                if (subject == null || subject.isEmpty()) {
                    throw new IllegalArgumentException("Email subject is required.");
                }
                if (body == null || body.isEmpty()) {
                    throw new IllegalArgumentException("Email body is required.");
                }

                // Create a new SimpleMailMessage and set properties
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(body);

                // Send the email using the JavaMailSender
                javaMailSender.send(message);
                //save the last timestamp for when a message was sent
                lastRequestTime = LocalTime.now();
            } catch (Exception e) {
                // Handle exceptions and log errors
                // You can log the error message using a logging framework like Log4j or SLF4J
                e.printStackTrace();
            }
        }
    }
}
