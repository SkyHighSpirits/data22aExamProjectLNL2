package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Company;
import com.example.data22aexamprojectlnl2.models.Security;

import java.util.Optional;

public interface SecurityService {
    Security saveSecurity(Security security);
    Optional<Security> getSecurityByUsernameAndPassword(String username, String password);
}
