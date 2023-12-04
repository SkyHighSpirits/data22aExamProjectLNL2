package com.example.data22aexamprojectlnl2.controllers;

import com.example.data22aexamprojectlnl2.models.Operation;
import com.example.data22aexamprojectlnl2.services.OperationService;
import com.example.data22aexamprojectlnl2.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OperationsController {

    @Autowired
    OperationService operationService;

    @Autowired
    SecurityService securityService;

    @PostMapping("/editOperation")
    public ResponseEntity<String> editOperation()
    {
        return null;
    }

    @PostMapping("/deleteOperation")
    public ResponseEntity<String> deleteOperation()
    {
        return null;
    }

    @PostMapping("/createOperation")
    public ResponseEntity<String> createOperation()
    {
        return null;
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
