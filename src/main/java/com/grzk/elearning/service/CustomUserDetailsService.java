package com.grzk.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grzk.elearning.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.grzk.elearning.model.User userRecord = userRepository.findByEmailOrUsername(username,username);
		if (userRecord==null)
			throw  new UsernameNotFoundException("Username :"+username+" not found");
		
		User user = new User(username, 
				userRecord.getPassword(),
				userRecord.isEnabled(), 
				true, 
				true, 
				true,
				userRecord.getAuthorities()
		);
		
		return user;
	}
}
