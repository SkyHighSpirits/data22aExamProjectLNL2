package com.example.data22aexamprojectlnl2.repositories;

import com.example.data22aexamprojectlnl2.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer>
{
    // Du kan tilføje egne metoder her, hvis nødvendigt.
}
