package com.grzk.elearning.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grzk.elearning.dto.LoginRequest;
import com.grzk.elearning.model.User;
import com.grzk.elearning.service.UserService;



@RestController
public class SessionController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public User login(@RequestBody LoginRequest loginRequest){
		User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
		return user;
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(Principal principal,Model model){
		model.addAttribute("user", new User());
		return "login";
	}
}
