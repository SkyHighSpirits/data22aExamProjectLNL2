package com.example.data22aexamprojectlnl2.repositories;

import com.example.data22aexamprojectlnl2.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>
{
    // Du kan tilføje egne metoder her, hvis nødvendigt.
    List<Image> getImagesByPosterId(int poster_id);

    List<Image> findByPosterId(int id);
}