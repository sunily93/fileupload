package com.example.FileUploadDownload.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FileUploadDownload.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer>{

	Optional<FileData> findByName(String fileName);

}
