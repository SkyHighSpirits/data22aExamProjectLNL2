package com.example.data22aexamprojectlnl2.repositories;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.services.ImageService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    // Du kan tilføje egne metoder her, hvis nødvendigt.
    List<Image> getImagesByPosterId(int poster_id);

    List<Image> findByPosterId(int id);
}