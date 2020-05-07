package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.util.Contant;
import com.laptrinhjavaweb.util.ConvertFileToBase64;

@Component
public class BookConverter {
	public BookDTO toDTO (BookEntity item) {
		BookDTO dto = new BookDTO();
		dto.setId(item.getId());
		dto.setTitle(item.getTitle());
		dto.setShortDescription(item.getShortDescription());
		dto.setImg(item.getImg());
		dto.setPrice(item.getPrice());
		dto.setDiscount(item.getDiscount());
		dto.setFileBase64(ConvertFileToBase64.encoder(Contant.PATH + item.getImg()));
		/*convert to base64 by path(link to folder), file name*/
		return dto;
	}
	
	public BookEntity toEntity(BookDTO dto,BookEntity result) {
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setPrice(dto.getPrice());
		result.setDiscount(dto.getDiscount());
		result.setImg(dto.getImg());
		return result;
	}
	
	public BookEntity toEntity(BookDTO dto) {
		BookEntity result = new BookEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setPrice(dto.getPrice());
		result.setDiscount(dto.getDiscount());
		result.setImg(dto.getImg());
		return result;
	}
}
