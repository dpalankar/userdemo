package com.example.userdemo;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	User createUser(User user);
	
	User updateUser(User user) throws UserException;
	
	User getUserById(long id) throws UserException;

	void deleteUser(long id) throws UserException;

	User updatePartial(Map<String, String> update, Long id) throws UserException;

	Page<User> getAllUsers(Pageable page);
	
}
