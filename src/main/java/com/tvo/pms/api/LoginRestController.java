package com.tvo.pms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tvo.pms.service.UserService;

@RestController
public class LoginRestController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/rest/login" }, method = RequestMethod.POST)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
