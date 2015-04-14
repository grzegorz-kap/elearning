package com.grzk.elearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SessionController {
	
	@RequestMapping(value="/login")
	public String newForm(Model model){
		return "login";
	}
}
