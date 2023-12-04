package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationService
{
    Operation saveOperation(Operation operation);

    Optional<Operation> getOperationById(int id);

    List<Operation> getAllOperations();

    void deleteOperation(int id);
}
