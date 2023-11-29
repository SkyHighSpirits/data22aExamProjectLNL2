package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Company;
import java.util.List;
import java.util.Optional;


public interface CompanyService {
    Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(int id);
    Company updateCompany(Company company, int id);
    void deleteCompany(int id);

    Optional<Company> getCompanyByUsernameAndPassword(String username, String password);
}