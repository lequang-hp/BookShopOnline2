package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BookConverter;
import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.repository.BookRepository;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.book.BookRepositoryCustom;
import com.laptrinhjavaweb.service.IBookService;

@Service
public class BookService implements IBookService{

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookConverter bookConverter;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepositoryCustom bookReposiroryCustom;
	
	@Override
	public List<BookDTO> findAll() {
		List<BookDTO> model = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findAll();
		for(BookEntity item: entities) {
			BookDTO dto = bookConverter.toDTO(item);
			model.add(dto);
		}
		return model;
	}
	
	@Override
	public List<BookDTO> findAll(Pageable pageable) {
		List<BookDTO> model = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findAll(pageable).getContent();
		for(BookEntity item: entities) {
			BookDTO dto = bookConverter.toDTO(item);
			model.add(dto);
		}
		return model;
	}
	
	
	@Override
	public List<BookDTO> findByCategoryId(long categoryID) {
		List<BookDTO> model = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findByCategoryId(categoryID);
		for(BookEntity item: entities) {
			BookDTO dto = bookConverter.toDTO(item);
			model.add(dto);
		}
		return model;
	}

	@Override
	public BookDTO findById(long id) {
		BookEntity entity = bookRepository.findOne(id);
		BookDTO dto = bookConverter.toDTO(entity);
		return dto;
	}

	@Override
	@Transactional
	public BookDTO save(BookDTO bookDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByName(bookDTO.getCategoryName());
        BookEntity bookEntity = new BookEntity();

        if (bookDTO.getId() != null){
            BookEntity oldBook = bookRepository.findOne(bookDTO.getId());
            bookEntity = bookConverter.toEntity(bookDTO,oldBook);
        }
        else{
            bookEntity = bookConverter.toEntity(bookDTO);
        }

        bookEntity.setCategory(categoryEntity);
        bookEntity = bookRepository.save(bookEntity);
        bookDTO = bookConverter.toDTO(bookEntity);
        return bookDTO;
	}

	@Override
	public int getTotalItem() {
		return (int) bookRepository.count();
	}
	
	@Override
	public List<BookDTO> findAll(BookDTO bookDTO, Pageable pageable) {
		List<BookEntity> entities = (List<BookEntity>) bookReposiroryCustom.findAll(bookDTO, pageable);
		List<BookDTO> model = new ArrayList<>();
		for(BookEntity item:entities) {
			BookDTO dto = bookConverter.toDTO(item);
			model.add(dto);
		}
		return model;
	}

	@Override
	public Long getTotalItem(BookDTO bookDTO) {
		Long total = bookReposiroryCustom.getTotalItem(bookDTO);
		return total;
	}

	@Override
	@Transactional
	public void deleteBook(long[] ids) {
		for(long id: ids) {
			bookRepository.delete(id);
		}
	}
}
