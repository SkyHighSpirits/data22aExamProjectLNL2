package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.services.CompanyService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    void updateCompanyInformationTest() {
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