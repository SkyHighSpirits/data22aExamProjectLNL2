package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Operation;
import com.example.data22aexamprojectlnl2.models.Security;
import com.example.data22aexamprojectlnl2.services.OperationService;
import com.example.data22aexamprojectlnl2.services.PasswordHashingService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OperationsController {

    final PasswordHashingService passwordHashing = new PasswordHashingService();
    @Autowired
    OperationService operationService;

    @Autowired
    SecurityService securityService;

    @PostMapping("/editOperation")
    public ResponseEntity<String> editOperation(
            @RequestParam int id,
            @RequestParam String operationName,
            @RequestParam String operationDescription,
            @RequestParam String username,
            @RequestParam String password
    )
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        System.out.println(checkSecurity.isPresent());
        if (checkSecurity.isPresent())
        {
            try
            {
                Operation operation = new Operation();
                operation.setOperation_Id(id);
                operation.setOperation_Name(operationName);
                operation.setOperation_Desription(operationDescription);

                operationService.updateOperation(operation,id);

                return ResponseEntity.status(HttpStatus.OK).body("Operation was updated succesfully");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating operation");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
    }

    @PostMapping("/deleteOperation")
    public ResponseEntity<String> deleteOperation()
    {
        return null;
    }

    @PostMapping("/createOperation")
    public ResponseEntity<String> createOperation(@RequestParam("operation_name") String operationName,
                                                  @RequestParam("operation_description") String operationDescription,
                                                  @RequestParam("username") String username,
                                                  @RequestParam("password") String password)
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        System.out.println(checkSecurity.isPresent());
        if (checkSecurity.isPresent())
        {
            try
            {

                Operation operation = new Operation();
                operation.setOperation_Name(operationName);
                operation.setOperation_Desription(operationDescription);

                operationService.saveOperation(operation);

                return ResponseEntity.status(HttpStatus.OK).body("Operation was created successfully");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating operation");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
    }

    @GetMapping("/getOperation")
    public ResponseEntity<Operation> getOperationById(@RequestParam int id)
    {
        if(operationService.getOperationById(id).isPresent())
        {
            Operation operation = operationService.getOperationById(id).get();
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllOperations")
    public ResponseEntity<List<Operation>> getAllOperations()
    {
        List<Operation> operations;

        operations = operationService.getAllOperations();
        if(operations.isEmpty() || operations == null)
        {
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }
        return new ResponseEntity<>(operations, HttpStatus.NOT_FOUND);
    }


}
