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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
public class OperationsController {


    //Autowires and objects imported to get access to functions in classes and interfaces connected to the JPA repository

    final PasswordHashingService passwordHashing = new PasswordHashingService();
    @Autowired
    OperationService operationService;

    @Autowired
    SecurityService securityService;

    //PostMapping that will update an operation in the database

    @PutMapping("/editOperation")
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

    //PostMapping that will delete an operation in the database

    @DeleteMapping("/deleteOperation")
    public ResponseEntity<String> deleteOperation(@RequestParam("operation_id") int operationId,
                                                  @RequestParam("username") String username,
                                                  @RequestParam("password") String password) {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);

        if (checkSecurity.isPresent()) {
            Optional<Operation> checkOperation = operationService.getOperationById(operationId);

            if (checkOperation.isPresent()) {
                operationService.deleteOperation(operationId);
                return ResponseEntity.ok("Operation slettet");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operation ikke fundet");
            }
        } else {
            System.out.println("Adgangskoden er muligvis forkert");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //PostMapping that will create an operation in the database
    @PostMapping("/createOperation")
    public ResponseEntity<String> createOperation(@RequestParam("operation_name") String operationName,
                                                  @RequestParam("operation_description") String operationDescription,
                                                  @RequestParam("username") String username,
                                                  @RequestParam("password") String password)
    {
        //Checks the admin login before performing the update. If not correct, will give error message
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        System.out.println(checkSecurity.isPresent());
        //If the security object is present the username and password was correct
        if (checkSecurity.isPresent())
        {
            try
            {
                //creates a temporary object that will be sent to the database and store its information
                Operation operation = new Operation();
                operation.setOperation_Name(operationName);
                operation.setOperation_Desription(operationDescription);

                operationService.saveOperation(operation);

                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating operation");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //GetMapping that will find a specific object in the database based on a id
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

    //GetMapping that will return all operations that is stored in the database
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

    @GetMapping("/getAllOperationsIfPassword")
    public ResponseEntity<List<Operation>> getAllOperationsIfPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )
    {
        String hashedUsername = passwordHashing.doHashing(username);
        String hashedPassword = passwordHashing.doHashing(password);
        Optional<Security> checkSecurity = securityService.getSecurityByUsernameAndPassword(hashedUsername, hashedPassword);
        if(checkSecurity.isPresent()) {

            List<Operation> operations;

            operations = operationService.getAllOperations();
            if (!operations.isEmpty() || operations != null) {
                return new ResponseEntity<>(operations, HttpStatus.OK);
            }
            return new ResponseEntity<>(operations, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
