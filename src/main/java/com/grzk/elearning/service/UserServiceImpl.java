package com.grzk.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grzk.elearning.model.Authority;
import com.grzk.elearning.model.User;
import com.grzk.elearning.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	@Transactional
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.resetAuthorities();
		user.addAuthority(new Authority("ROLE_USER"));
		user.setEnabled(true);
		return userRepository.save(user);
	}

}
