package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Company;
import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(int id);
    Company updateCompany(Company company, int id);
    void deleteCompany(int id);
}