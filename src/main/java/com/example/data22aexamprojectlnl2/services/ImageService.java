package com.example.data22aexamprojectlnl2.services;

import com.example.data22aexamprojectlnl2.models.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image saveImage(Image image);
    Optional<Image> getImageById(int id);
    List<Image> getAllImages();
    void deleteImage(int id);
}
