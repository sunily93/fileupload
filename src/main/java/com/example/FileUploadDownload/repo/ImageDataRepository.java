package com.example.FileUploadDownload.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FileUploadDownload.entity.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData, Integer>{

	Optional<ImageData> findByName(String fileName);
}
