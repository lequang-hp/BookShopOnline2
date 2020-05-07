package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

@RestController(value = "userAPIOfAdmin")
public class UserAPI {
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/find-all-user")
	public UserDTO findAll(@RequestBody UserDTO userDTO) {
		userDTO.setListResult(userService.findAll(userDTO));
		userDTO.setTotalPage((int) Math.ceil((double) (userService.getTotalItem(userDTO)) / userDTO.getLimit()));
		return userDTO;
	}
}
