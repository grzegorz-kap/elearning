package com.grzk.elearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grzk.elearning.model.User;

@Controller
public class RegistrationController {
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String newForm(Model model){
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String create(@RequestBody User user,Model model){
		return "redirect:/";
	}
}
