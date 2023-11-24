package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Operation;
import com.example.data22aexamprojectlnl2.repositories.OperationRepository;
import com.example.data22aexamprojectlnl2.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    /*
    @Override
    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }
    */

    @Override
    public Optional<Operation> getOperationById(int id) {
        return operationRepository.findById(id);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation saveOperation(Operation operation) {
        return null;
    }

    @Override
    public void deleteOperation(int id) {

    }
}
