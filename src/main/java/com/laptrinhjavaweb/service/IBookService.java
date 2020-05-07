package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.BookDTO;

public interface IBookService {
	List<BookDTO> findByCategoryId(long categoryID);
	BookDTO findById(long id);
	BookDTO save(BookDTO dto);
	int getTotalItem();
	Long getTotalItem(BookDTO bookDTO);
	List<BookDTO> findAll(BookDTO bookDTO, Pageable pageable);
	List<BookDTO> findAll();
	List<BookDTO> findAll(Pageable pageable);
	void deleteBook(long[] ids);
}
