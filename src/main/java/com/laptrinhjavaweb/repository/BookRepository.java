package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BookEntity;


public interface BookRepository extends JpaRepository<BookEntity, Long>{
	Page<BookEntity> findByTitleContainingIgnoreCase(String searchValue, Pageable pageable);
	long countByTitleContainingIgnoreCase(String searchValue);
	long countByPriceGreaterThanEqual(float price);
	long countByPriceLessThanEqual(float price);
	List<BookEntity> findByCategoryId(Long categoryId);
	Page<BookEntity> findByPriceGreaterThanEqual(float minPrice,Pageable pageable);
	Page<BookEntity> findByPriceLessThanEqual(float maxPrice,Pageable pageable);
}
