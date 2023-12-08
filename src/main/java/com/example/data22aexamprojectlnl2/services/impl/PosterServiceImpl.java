package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Poster;
import com.example.data22aexamprojectlnl2.repositories.PosterRepository;
import com.example.data22aexamprojectlnl2.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosterServiceImpl implements PosterService
{

    //Autowires in the regarding repository to access the JPArepository
    //All the funtioncs here overrides the functions that was made in the interface regarding each model
    private final PosterRepository posterRepository;

    @Autowired
    public PosterServiceImpl(PosterRepository posterRepository)
    {
        this.posterRepository = posterRepository;
    }

    //saves a poster object to the database
    @Override
    public Poster savePoster(Poster poster)
    {
        return posterRepository.save(poster);
    }

    //retrieves a specific poster based on an id
    @Override
    public Optional<Poster> getPosterById(int id)
    {
        return posterRepository.findById(id);
    }

    //retrieves all posters from the database
    @Override
    public List<Poster> getAllPosters()
    {
        return posterRepository.findAll();
    }

    //deletes a poster in the database based on an id
    @Override
    public void deletePoster(int id)
    {
        posterRepository.deleteById(id);
    }
}
