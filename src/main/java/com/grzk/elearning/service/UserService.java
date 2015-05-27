package com.grzk.elearning.service;

import java.util.List;

import com.grzk.elearning.model.User;

public interface UserService {
	
	List<User> findAll();
	User findByUsername(String username);
	User save(User save);
	
	boolean isUsernameAvailable(String username);
	boolean isEmailAvailable(String email);
	
	
	User login(String login,String password); /* Should be moved to other service */
}

