package com.grzk.elearning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grzk.elearning.dto.UserRegisterRequest;
import com.grzk.elearning.factory.UserFactory;
import com.grzk.elearning.model.User;
import com.grzk.elearning.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFactory userFactory;


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User create(@RequestBody @Valid  UserRegisterRequest userDto) {
		User user = userFactory.createUserFromRegisterRequest(userDto);
		user = userService.save(user);
		if(user!=null)
			userService.login(user.getUsername(),userDto.getPassword());
		return user;
	}
	
}
