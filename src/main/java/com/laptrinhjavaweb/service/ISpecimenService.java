package com.laptrinhjavaweb.service;

import org.springframework.web.multipart.MultipartFile;

public interface ISpecimenService {
	void saveImageFile(MultipartFile imageFile) throws Exception;
	
}
