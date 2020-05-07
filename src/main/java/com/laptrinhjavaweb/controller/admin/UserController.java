package com.laptrinhjavaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "userControllerOfAdmin")
public class UserController {
	@RequestMapping("/admin/user/list")
	public ModelAndView userAccountList() {
		ModelAndView mav = new ModelAndView("admin/user/list");
		return mav;
	}
}
