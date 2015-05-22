package com.grzk.elearning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grzk.elearning.dto.UserRegisterRequest;
import com.grzk.elearning.model.Authority;
import com.grzk.elearning.model.User;
import com.grzk.elearning.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User create(@RequestBody @Valid  UserRegisterRequest userDto) {
		User user = this.preperateNewUserFromDto(userDto);
		user = userService.save(user);
		if(user!=null)
			userService.login(user.getUsername(),userDto.getPassword());
		return user;
	}
	
	private User preperateNewUserFromDto(UserRegisterRequest userDto){
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.resetAuthorities();
		user.addAuthority(new Authority("ROLE_USER"));
		return user;
	}

	
}
