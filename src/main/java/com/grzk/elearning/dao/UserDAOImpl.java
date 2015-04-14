package com.grzk.elearning.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.grzk.elearning.model.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<User> list() {
		String query = "FROM User";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) sessionFactory.getCurrentSession()
				.createQuery(query).list();
		return list;
	}

	@Override
	public User byId(long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
	

}
