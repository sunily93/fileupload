package com.example.FileUploadDownload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadDownload.entity.FileData;
import com.example.FileUploadDownload.entity.ImageData;
import com.example.FileUploadDownload.repo.FileDataRepository;
import com.example.FileUploadDownload.utils.ImageUtils;

@Service
public class FileDataService {

	@Autowired
	private FileDataRepository repository;
	
	private final String FOLDER_URL="F:/uploadfiles/";

	public String uploadFileToFileSystem(MultipartFile file) throws IOException
	{
		String path=FOLDER_URL+file.getOriginalFilename();
		repository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(path).build());
		file.transferTo(new File(path));
		if(path!=null)
		{
			return "file upload successfully:- "+path;
		}
		return null;
	}
	public byte[] dwonloadImageFromFileSystem(String fileName) throws IOException
	{
		Optional<FileData> findByName = repository.findByName(fileName);
		String filePath = findByName.get().getFilePath();
		byte[] image = Files.readAllBytes(new File(filePath).toPath());
		return image;
	}
}
