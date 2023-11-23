package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Poster;
import com.example.data22aexamprojectlnl2.repositories.PosterRepository;
import com.example.data22aexamprojectlnl2.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosterServiceImpl implements PosterService {

    private final PosterRepository posterRepository;

    @Autowired
    public PosterServiceImpl(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    @Override
    public Poster savePoster(Poster poster) {
        return posterRepository.save(poster);
    }

    @Override
    public Optional<Poster> getPosterById(int id) {
        return posterRepository.findById(id);
    }

    @Override
    public List<Poster> getAllPosters() {
        return posterRepository.findAll();
    }

    @Override
    public void deletePoster(int id) {
        posterRepository.deleteById(id);
    }
}
