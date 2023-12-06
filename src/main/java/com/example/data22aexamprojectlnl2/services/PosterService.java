package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Poster;

import java.util.List;
import java.util.Optional;

public interface PosterService
{
    Poster savePoster(Poster poster);

    Optional<Poster> getPosterById(int id);

    List<Poster> getAllPosters();

    void deletePoster(int id);
}

