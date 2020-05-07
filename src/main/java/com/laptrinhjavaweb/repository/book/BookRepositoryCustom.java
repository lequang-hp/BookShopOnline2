package com.laptrinhjavaweb.repository.book;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.BookDTO;

public interface BookRepositoryCustom {
	List<?> findAll(BookDTO bookDTO, Pageable pageable);
	Long getTotalItem(BookDTO bookDTO);
}
