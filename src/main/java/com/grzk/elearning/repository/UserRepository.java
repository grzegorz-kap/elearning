package com.grzk.elearning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grzk.elearning.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	List<User> findAll();
	
	User findOne(Long id);
	
	User findOneByUsername(String username);
	
	User findOneByEmailOrUsername(String email,String username);
	
	Long countByEmailOrUsername(String email,String username);
	
	@SuppressWarnings("unchecked") 
	User save(User save);
}
