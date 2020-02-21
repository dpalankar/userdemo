package com.example.userdemo.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.userdemo.exception.UserException;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(User user) throws UserException {
		User dbuser = getUserById(user.getId());
		user = userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(long id) throws UserException {
		try {
			if(userRepository.existsById(id))
				userRepository.deleteById(id);
		} catch (Exception e) {
			throw new UserException("User not deleted");
		}
	}

	@Override
	public User getUserById(long id) throws UserException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserException("User not found");
		}
		return user.get();
	}

	@Override
	public User updatePartial(Map<String, String> update, Long id) throws UserException {
		User user = getUserById(id);
		boolean isDataupdated = false;
		if (!StringUtils.isEmpty(update.get("name"))) {
			user.setName(update.get("name"));
			isDataupdated = true;
		}
		if (!StringUtils.isEmpty(update.get("email"))) {
			user.setEmail(update.get("email"));
			isDataupdated = true;
		}
		if (!StringUtils.isEmpty(update.get("address"))) {
			user.setAddress(update.get("address"));
			isDataupdated = true;
		}
		if(isDataupdated)
			user = userRepository.save(user);
		else
			new UserException("No change in user info");
		return user;
	}

	@Override
	public Page<User> getAllUsers(Pageable page) {
		Page<User> users = userRepository.findAll(page);
		return users;
	}

}
