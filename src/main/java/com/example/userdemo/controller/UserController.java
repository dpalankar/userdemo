package com.example.userdemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.userdemo.exception.UserException;
import com.example.userdemo.model.User;
import com.example.userdemo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<String> defaultMethod(){
		return new ResponseEntity<>("Welcome ", HttpStatus.OK);
	}
	
	@GetMapping("/welcome/{name}")
	public ResponseEntity<String> welcomeMethod(@PathVariable String name){
		return new ResponseEntity<>("Welcome "+name, HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		user = userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException{
		user = userService.updateUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping("/user/{id}")
	public ResponseEntity<User> updatePartial(@RequestBody Map<String, String> update, @PathVariable Long id) throws UserException {
		User user = userService.updatePartial(update,id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws UserException{
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) throws UserException{
		User user =  userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) throws UserException{
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
	}
}
