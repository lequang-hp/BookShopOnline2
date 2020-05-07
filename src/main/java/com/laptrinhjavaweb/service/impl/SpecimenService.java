package com.laptrinhjavaweb.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.laptrinhjavaweb.service.ISpecimenService;
import com.laptrinhjavaweb.util.Contant;

@Service
public class SpecimenService implements ISpecimenService{

	@Override
	public void saveImageFile(MultipartFile imageFile) throws Exception{
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(Contant.PATH + imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}	
}
