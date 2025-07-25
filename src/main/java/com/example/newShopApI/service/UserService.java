package com.example.newShopApI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.User;
import com.example.newShopApI.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUserService(){
		return userRepository.findAll();
	}
	
	public User createNewUserService(User user) {
		return userRepository.save(user);
	}
	
	public User findUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
	}
	
	public User updateUserService(Long userId, User userDetails) {
		User user = findUserById(userId);
		if(userDetails.getUserName() != null) user.setUserName(userDetails.getUserName());
		if(userDetails.getUserEmail() != null) user.setUserEmail(userDetails.getUserEmail());
		
		userRepository.save(user);
		return user;
	}
}
