package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Operation;
import com.example.data22aexamprojectlnl2.repositories.OperationRepository;
import com.example.data22aexamprojectlnl2.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    //Autowires in the regarding repository to access the JPArepository
    //All the funtioncs here overrides the functions that was made in the interface regarding each model
    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository)
    {
        this.operationRepository = operationRepository;
    }

    /*
    @Override
    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }
    */

    //retrieves an operation based on an id
    @Override
    public Optional<Operation> getOperationById(int id)
    {
        return operationRepository.findById(id);
    }

    //retrieves all operations in the database
    @Override
    public List<Operation> getAllOperations()
    {
        return operationRepository.findAll();
    }

    //saves an operation object to the database
    @Override
    public Operation saveOperation(Operation operation)
    {
        return operationRepository.save(operation);
    }

    //deletes an operation from the database based on an id
    @Override
    public void deleteOperation(int id)
    {

    }
}
