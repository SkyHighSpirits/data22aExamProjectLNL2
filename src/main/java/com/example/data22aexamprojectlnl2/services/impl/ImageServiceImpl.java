package com.example.data22aexamprojectlnl2.services.impl;

import com.example.data22aexamprojectlnl2.models.Image;
import com.example.data22aexamprojectlnl2.repositories.ImageRepository;
import com.example.data22aexamprojectlnl2.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService
{

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository)
    {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(Image image)
    {
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> getImageById(int id)
    {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> getAllImages()
    {
        return imageRepository.findAll();
    }

    @Override  //til at finde billeder der passer til en specifik post
    public List<Image> getImagesByPosterId(int poster_id)
    {
        return imageRepository.getImagesByPosterId(poster_id);
    }

    @Override
    public void deleteImage(int id)
    {
        imageRepository.deleteById(id);
    }
}