package com.example.data22aexamprojectlnl2.repositories;

import com.example.data22aexamprojectlnl2.models.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Integer>
{
    // Du kan tilføje egne metoder her, hvis nødvendigt.
}
