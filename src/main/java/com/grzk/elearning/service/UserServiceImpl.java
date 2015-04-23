package com.grzk.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserDetailsService customUserDetailsService;
	
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


	@Override
	@Transactional
	public void login(User user,String password) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
		UsernamePasswordAuthenticationToken auth  = new UsernamePasswordAuthenticationToken(
															userDetails,
															password,
															userDetails.getAuthorities()
														);
		authenticationManager.authenticate(auth);
		if(auth.isAuthenticated())
			SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
