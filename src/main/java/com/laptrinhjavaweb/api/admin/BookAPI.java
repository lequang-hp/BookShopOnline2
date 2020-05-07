package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.IBookService;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.ISpecimenService;

@RestController(value = "bookAPIOfAdmin")
public class BookAPI {

	@Autowired
	private IBookService bookService;
	@Autowired
	private ISpecimenService specimenService;
	@Autowired
	private ICategoryService categoryService;

	@PostMapping("api/book")
	public BookDTO createBook(@ModelAttribute BookDTO dto) {
		try {
			specimenService.saveImageFile(dto.getImageFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dto.setImg(dto.getImageFile().getOriginalFilename());
		return bookService.save(dto);
	}

	@PutMapping("/api/book")
	public BookDTO updateBook(@RequestBody BookDTO dto) {
		return bookService.save(dto);
	}
	
	@DeleteMapping("api/book")
	public void deleteBook(long[] ids) {
		bookService.deleteBook(ids);
	}
	
	@PostMapping("/api/find-all-book")
	public BookDTO findAll(@RequestBody BookDTO bookDTO) {
		Pageable pageable = new PageRequest(bookDTO.getPage() - 1, bookDTO.getLimit());
		bookDTO.setListResult(bookService.findAll(bookDTO, pageable));
		bookDTO.setTotalPage((int) Math.ceil((double) (bookService.getTotalItem(bookDTO)) / bookDTO.getLimit()));
		return bookDTO;
	}
	
	@GetMapping("/api/find-all-category")
	public CategoryDTO findAllCategory() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setListResult(categoryService.findAll());
		return categoryDTO;
	}
}
