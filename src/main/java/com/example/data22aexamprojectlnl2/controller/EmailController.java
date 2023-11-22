package com.example.data22aexamprojectlnl2.controller;

import com.example.data22aexamprojectlnl2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public void sendEmail() {
        String to = "dovser.chinastore@gmail.com";
        String subject = "Hello from SMTP";
        String body = "Nu kan vi sende mails med vores service";

        emailService.sendEmail(to, subject, body);
    }
}
