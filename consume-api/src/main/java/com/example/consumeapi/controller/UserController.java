package com.example.consumeapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumeapi.exception.NotChromeException;
import com.example.consumeapi.model.User;
import com.example.consumeapi.repository.UserRepository;
import com.example.consumeapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	// Get All Notes
	@GetMapping("/users")
	public List<User> getAllUsers(@RequestHeader(value="User-Agent") String userAgent) 
		throws NotChromeException{
		return userService.getalluserService(userAgent);
	}

	// Create a new Note
	@PostMapping("/users")
	public User createUsers(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PostMapping("/users/input")
	public User createOutsideUsers(@Valid @RequestBody User user) {
		userService.usecreateOutsideUsers(user);
		return null;
	}

	// Get a Single Note
	@GetMapping("/users/filter")
	public List<User> getUserById(@RequestParam(defaultValue = "-1") long userId,
			@RequestParam(defaultValue = "null") String isCompleted,
			@RequestHeader(value="User-Agent") String userAgent) throws NotChromeException{
		return userService.getUserByIdService(userId,isCompleted,userAgent);	
	}

	// Update a User
	@PutMapping("/users/{id}")
	public User updateNote(@PathVariable(value = "id") Long id, 
	@Valid @RequestBody User userDetails) {	
		return userService.updateUser(id,userDetails);
	}

	// Delete a User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		return userService.deleteUserService(userId);
	}
}