package com.example.FileUploadDownload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadDownload.service.FileDataService;

@RestController
@RequestMapping("file")
public class FileDataController {

	@Autowired
	private FileDataService dataService;

	@PostMapping
	public ResponseEntity<?> uploadFileToFileSystem(@RequestParam("image")MultipartFile file) throws IOException
	{
		String uploadFile = dataService.uploadFileToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> dwonloadImageFromFileSystem(@PathVariable String fileName) throws IOException
	{
		byte[] fileSystem = dataService.dwonloadImageFromFileSystem(fileName);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(fileSystem); 
	}
}
