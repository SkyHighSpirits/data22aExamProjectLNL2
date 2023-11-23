package com.example.data22aexamprojectlnl2.repositories;

import com.example.data22aexamprojectlnl2.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    // Du kan tilføje egne metoder her, hvis nødvendigt.
}