package com.example.consumeapi.service;

import com.example.consumeapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.consumeapi.constant.AppConstants;
import com.example.consumeapi.exception.NotChromeException;
import com.example.consumeapi.exception.ResourceNotFoundException;
import com.example.consumeapi.model.OutsideUser;
import com.example.consumeapi.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User updateUser(Long id, User user) {
		User savedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

		savedUser.setTitle(user.getTitle());
		savedUser.setCompleted(user.isCompleted());
		savedUser.setUserId(user.getUserId());

		userRepository.save(savedUser);
		return savedUser;
	}
	
	public List<User> getalluserService(String userAgent) throws NotChromeException{
		Pattern pattern = Pattern.compile(".Chrome.", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(userAgent);
		boolean matchFound = matcher.find();
		if (!matchFound) {
			throw new NotChromeException(userAgent);
		}
		return userRepository.findAll();
	}

	public List<User> getUserByIdService(Long userId, String isCompleted, String userAgent) throws NotChromeException {
		// String agentArray[] = userAgent.split(" ");
		Pattern pattern = Pattern.compile(".Chrome.", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(userAgent);
		boolean isChrome = matcher.find();
		if (!isChrome) {
			throw new NotChromeException(userAgent);
		}

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

	public User usecreateOutsideUsers(User user) {
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
