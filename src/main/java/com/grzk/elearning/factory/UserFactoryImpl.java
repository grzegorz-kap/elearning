package com.grzk.elearning.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.grzk.elearning.dto.UserRegisterRequest;
import com.grzk.elearning.model.Authority;
import com.grzk.elearning.model.User;


@Component
public class UserFactoryImpl implements UserFactory {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUserFromRegisterRequest(UserRegisterRequest registerRequest) {
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.resetAuthorities();
		user.addAuthority(new Authority("ROLE_USER"));
		return user;
	}

}
