package com.example.userdemo.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.userdemo.exception.UserException;
import com.example.userdemo.model.User;

public interface UserService {

	User createUser(User user);
	
	User updateUser(User user) throws UserException;
	
	User getUserById(long id) throws UserException;

	void deleteUser(long id) throws UserException;

	User updatePartial(Map<String, String> update, Long id) throws UserException;

	Page<User> getAllUsers(Pageable page);
	
}
