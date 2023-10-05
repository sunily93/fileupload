package com.example.FileUploadDownload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadDownload.entity.ImageData;
import com.example.FileUploadDownload.repo.ImageDataRepository;
import com.example.FileUploadDownload.utils.ImageUtils;

@Service
public class StorageService {

	@Autowired
	private ImageDataRepository repository;

	public String uploadFile(MultipartFile file) throws IOException
	{
		ImageData imageData = repository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.file(ImageUtils.compressImage(file.getBytes())).build());
		if(imageData!=null)
		{
			return "file upload successfully:- "+file.getOriginalFilename();
		}
		return null;
	}
	
	public byte[] dwonloadImage(String fileName)
	{
		Optional<ImageData> findByName = repository.findByName(fileName);
		byte[] image = ImageUtils.decompressImage(findByName.get().getFile());
		return image;
	}
}
