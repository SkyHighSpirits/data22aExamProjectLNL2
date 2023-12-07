package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Operation;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.services.OperationService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OperationsController.class)
class OperationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private OperationsController operationsController;

    @MockBean
    private OperationService operationService;

    @MockBean
    private SecurityService securityService;

    @Test
    void editOperationTest() throws Exception{
        //Mock the behavior of the companyService
        Mockito.when(securityService.getSecurityByUsernameAndPassword("a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5"))
                .thenReturn(Optional.of(new Security(1, "a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5")));

        // Mock the behavior of operationService

        mockMvc.perform(MockMvcRequestBuilders.put("/editOperation")
                        .param("id", "1")
                        .param("operationName", "FacadeIsolering")
                        .param("operationDescription", "Beskrivelse af virksomhed")
                        .param("username", "IlsoeAdmin")
                        .param("password", "0807")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Operation was updated succesfully"));

        // Verify that updateOperation method was called with the correct parameters
        Mockito.verify(operationService).updateOperation(
                Mockito.any(Operation.class),
                Mockito.eq(1) // Assuming 1 is the expected ID
        );
    }

    @Test
    void deleteOperationTest() throws Exception {
        //Mock the behavior of the companyService
        Mockito.when(securityService.getSecurityByUsernameAndPassword("a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5"))
                .thenReturn(Optional.of(new Security(1, "a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5")));

        Mockito.when(operationService.getOperationById(1))
                        .thenReturn(Optional.of(new Operation(1, "Opsætning af køkkener", "Køkkener")));

        // Mock the behavior of operationService

        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteOperation")
                .param("operation_id", "1")
                .param("username", "IlsoeAdmin")
                .param("password", "0807")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Operation slettet"));

        Mockito.verify(operationService).deleteOperation(
                Mockito.eq(1)
        );
    }
/*
    @Test
    void createOperationTest() throws Exception {
        //Mock the behavior of the companyService
        Mockito.when(securityService.getSecurityByUsernameAndPassword("a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5"))
                .thenReturn(Optional.of(new Security(1, "a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5")));

        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/createOperation")
                .param("operation_name", "FacadeIsolering")
                .param("operation_description", "Isolering af facader")
                .param("username", "IlsoeAdmin")
                .param("password", "0807")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that saveOperation method was called with the correct parameters
        Mockito.verify(operationService).saveOperation(Mockito.any(Operation.class)
        );

    }
*/


    /*@Test
    void getOperationById() throws Exception {
        // Mock the behavior of operationService
        int operationId = 1;
        Operation mockOperation = new Operation();
        mockOperation.setOperation_Name("Køkkener"); // Replace with actual initialization
        mockOperation.setOperation_Desription("Opsætning af køkkener");
        mockOperation.setPoster(null);

        Mockito.when(operationService.getOperationById(operationId)).thenReturn(Optional.of(mockOperation));

        // Setup MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).build();

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/getOperation")
                        .param("id", String.valueOf(operationId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockOperation.getOperation_Id()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation_Name").value(mockOperation.getOperation_Name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation_Desription").value(mockOperation.getOperation_Desription()))// Adjust this based on your Operation class
                .andExpect(MockMvcResultMatchers.jsonPath("$.poster").doesNotExist())
                // Add more assertions for other fields in the Operation class
                .andReturn();
    }

    @Test
    void getAllOperations() throws Exception {
    }

    @Test
    void getAllOperationsIfPassword() throws Exception {
    }
    
     */

}