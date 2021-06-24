package com.example.consumeapi.service;

import com.example.consumeapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.consumeapi.constant.AppConstants;
import com.example.consumeapi.exception.ResourceNotFoundException;
import com.example.consumeapi.model.OutsideUser;
import com.example.consumeapi.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User updateUser(Long id, User user) {
		User savedUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

		savedUser.setTitle(user.getTitle());
		savedUser.setCompleted(user.isCompleted());
		savedUser.setUserId(user.getUserId());

		userRepository.save(savedUser);
		return savedUser;
	}

	public List<User> getUserByIdService(Long userId, String isCompleted) {
		List<User> response = new ArrayList<>();
		boolean inputBoolean = true;

		if (userId > -1 && !isCompleted.equals("null")) {

			if (isCompleted.equals("true"))
				inputBoolean = true;
			else if (isCompleted.equals("false"))
				inputBoolean = false;

			response = userRepository.findAllByUserIdAndCompleted(userId, inputBoolean);

		} else if (userId > -1) {

			response = userRepository.findAllByUserId(userId);

		} else if (!isCompleted.equals("null")) {

			if (isCompleted.equals("true"))
				inputBoolean = true;
			else if (isCompleted.equals("false"))
				inputBoolean = false;

			response = userRepository.findAllByCompleted(inputBoolean);
		}
		return response;
	}
	public User UsecreateOutsideUsers(User user) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = AppConstants.ApiEndpoints.allUsers;
		ResponseEntity<OutsideUser[]> response = restTemplate.getForEntity(fooResourceUrl, OutsideUser[].class);

		for (OutsideUser i : response.getBody()) {
			if (i.getId() == user.getId()) {
				User newUser = new User(i.getUserId(), i.getId(), i.getTitle(), i.isCompleted());
				userRepository.save(newUser);
				return newUser;
			}
		}
		return null;
	}
	
	public ResponseEntity<?> deleteUserService(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);

		return ResponseEntity.ok().build();
	}
}
