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
    //Autowires in the regarding repository to access the JPArepository
    private final ImageRepository imageRepository;
    //All the funtioncs here overrides the functions that was made in the interface regarding each model


    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository)
    {
        this.imageRepository = imageRepository;
    }

    //saves an image object to the database
    @Override
    public Image saveImage(Image image)
    {
        return imageRepository.save(image);
    }

    //gets an image by id
    @Override
    public Optional<Image> getImageById(int id)
    {
        return imageRepository.findById(id);
    }

    //gets all images stored in the database
    @Override
    public List<Image> getAllImages()
    {
        return imageRepository.findAll();
    }

    //gets images based on a posterid
    @Override  //til at finde billeder der passer til en specifik post
    public List<Image> getImagesByPosterId(int poster_id)
    {
        return imageRepository.getImagesByPosterId(poster_id);
    }

    //deletes an images based on an id
    @Override
    public void deleteImage(int id)
    {
        imageRepository.deleteById(id);
    }
}