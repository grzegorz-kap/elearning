package com.grzk.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grzk.elearning.dao.UserDAO;
import com.grzk.elearning.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	@Transactional
	public List<User> list() {
		return userDAO.list();
	}

}
