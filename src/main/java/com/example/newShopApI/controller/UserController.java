package com.example.newShopApI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newShopApI.model.User;
import com.example.newShopApI.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUserService();
	}
	
	@PostMapping("/users")
	public User createNewUser(@Valid @RequestBody User user) {		
		return userService.createNewUserService(user);
	}
	
	@PutMapping("/users/{id}")
	public User updateNewUser(@PathVariable(value="id") Long userId, 
			@Valid @RequestBody User userDetails) {
		return userService.updateUserService(userId, userDetails);
	}
}
