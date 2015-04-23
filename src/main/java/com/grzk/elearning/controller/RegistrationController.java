package com.grzk.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grzk.elearning.model.User;
import com.grzk.elearning.service.UserService;
import com.grzk.elearning.validator.UserValidator;

@Controller
public class RegistrationController {
	
	@Autowired private UserValidator userValidator;
	@Autowired private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String newForm(Model model){
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String create(@Validated User user,BindingResult result){
		if(result.hasErrors())
			return "register";
		if (userService.save(user)==null)
			return "register";
		return "redirect:/";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(userValidator);
	}
}
