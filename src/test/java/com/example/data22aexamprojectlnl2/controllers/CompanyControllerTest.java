package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.services.CompanyService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @MockBean
    SecurityService securityService;

    @Test
    void updateCompanyInformationTest() throws Exception {
        // Mock the behavior of companyService
        Mockito.when(securityService.getSecurityByUsernameAndPassword("a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5"))
                .thenReturn(Optional.of(new Security(1, "a3062c20bac83330b0f7ab1b850bdda9", "d23422b17813e5eb024a4f3b4c9d97a5")));

        mockMvc.perform(MockMvcRequestBuilders.post("/update-company")
                        .param("company_title", "Ilsø Construct")
                        .param("company_description", "Velkommen til Odin's ")
                        .param("cvr", "12345678")
                        .param("telephone", "12 34 56 78")
                        .param("username", "IlsoeAdmin")
                        .param("password", "0807")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Company information updated successfully"));

        // Verify that updateCompany method was called with the correct parameters
        Mockito.verify(companyService).updateCompany(
                Mockito.any(Company.class),
                Mockito.eq(1) // Assuming 1 is the expected ID
        );
    }

    @Test
    void getCompanyTest() throws Exception {
        // Mock data for the company
        Company mockCompany = new Company();
        mockCompany.setId(1);
        mockCompany.setCompany_Title("Ilsø Construct");
        mockCompany.setCompany_Description("Velkommen til Odin's Borg");
        mockCompany.setCVR("12345678");
        mockCompany.setTelephone("12 34 56 78");

        // Mock the behavior of the companyService.getCompanyById(1) method
        Mockito.when(companyService.getCompanyById(1)).thenReturn(mockCompany);

        // Perform the GET request to your endpoint and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // This will print the response to the console
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company_Title").value("Ilsø Construct"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company_Description").value("Velkommen til Odin's Borg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cvr").value("12345678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telephone").value("12 34 56 78"));
    }
}