package com.grzk.elearning.service;

import java.util.List;

import com.grzk.elearning.model.User;

public interface UserService {
	List<User> findAll();
	User save(User save);
	User login(String login,String password);
}

