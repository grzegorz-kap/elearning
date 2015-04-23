package com.grzk.elearning.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grzk.elearning.model.User;



@Controller
public class SessionController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String newForm(Model model){
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(Principal principal,Model model){
		model.addAttribute("user", new User());
		return "login";
	}
}
