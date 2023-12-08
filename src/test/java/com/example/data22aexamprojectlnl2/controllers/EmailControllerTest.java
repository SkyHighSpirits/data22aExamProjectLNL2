package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.services.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @MockBean
    private EmailService emailService;

    @Autowired
    private MockMvc mockMvc;



    /*
    @Test
    void sendEmailTest() throws Exception {
        // Mock the behavior of the emailService
        Mockito.when(emailService.sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new ResponseEntity<>("Email sent successfully", HttpStatus.OK));

        // Setup MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/send-email")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .param("city", "Anytown")
                        .param("about", "Regarding something")
                        .param("message", "This is a test message.")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Email sent successfully"));

        // Verify that sendEmail method was called with the correct parameters
        Mockito.verify(emailService).sendEmail(
                Mockito.eq("dovser.chinastore@gmail.com"),
                Mockito.eq("Regarding something"),
                Mockito.eq("This is a test message.\n\nBeskeden blev sendt af John Doe fra Anytown\nSvar kan sendes på hans email adresse: john.doe@example.com\n")
        );
    }

     */
}