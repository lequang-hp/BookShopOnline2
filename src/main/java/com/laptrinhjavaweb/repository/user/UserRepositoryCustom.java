package com.laptrinhjavaweb.repository.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.UserDTO;

public interface UserRepositoryCustom {
	List<?> findAll(UserDTO userDTO, Pageable pageable);
	Long getTotalItem(UserDTO userDTO);
}
