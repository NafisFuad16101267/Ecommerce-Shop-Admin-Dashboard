package com.example.consumeapi.controller;

import com.example.consumeapi.exception.ResourceNotFoundException;
import com.example.consumeapi.model.OutsideUser;
import com.example.consumeapi.model.TestOutsiderClass;
import com.example.consumeapi.model.User;
import com.example.consumeapi.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.model.core.TypeRef;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    UserRepository userRepository;

    // Get All Notes
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/users")
    public User createUsers(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/users/input")
    public User createOutsideUsers(@Valid @RequestBody User user) {
    	RestTemplate restTemplate = new RestTemplate();
    	String fooResourceUrl = "https://jsonplaceholder.typicode.com/todos/";
    	//List<OutsiderUser> list = new ;
    	ResponseEntity<OutsideUser[]> response = restTemplate.getForEntity(fooResourceUrl, OutsideUser[].class);
    	
    	User sendResponse = new User((long)0,(long)0,"",false);
    	for(OutsideUser i: response.getBody()) {
    		if(i.getId() == user.getId()) {
    			//System.out.println(i);
    			User newUser = new User(i.getUserId(), i.getId(), i.getTitle(),  i.isCompleted());
    			sendResponse = newUser;
    			userRepository.save(newUser);
    		}
    	}
        return sendResponse;
    }

    // Get a Single Note
    @GetMapping("/users/filter")
    public List<User> getUserById(
    	@RequestParam(defaultValue = "-1") long userId, 
    	@RequestParam(defaultValue = "null") String isCompleted
    ) {
    	List<User> response = new ArrayList<>();
    	boolean logic = true;
   
    	
    	if(userId > -1 && !isCompleted.equals("null")) {
    		if(isCompleted.equals("true")) logic = true;
    		else if(isCompleted.equals("false")) logic = false;
    		response = userRepository.findAllByUserIdAndCompleted(userId, logic);
    		System.out.println("here");
    	} else if(userId > -1) {
    		response = userRepository.findAllByUserId(userId);
    	} else if(!isCompleted.equals("null")) {
    		if(isCompleted.equals("true")) logic = true;
    		else if(isCompleted.equals("false")) logic = false;
    		response = userRepository.findAllByCompleted(logic);
    	}
    	return response;
               // .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
    }

    // Update a User
    @PutMapping("/users/{id}")
    public User updateNote(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setTitle(userDetails.getTitle());
        user.setCompleted(userDetails.isCompleted());

        User updatedNote = userRepository.save(user);
        return updatedNote;
    }

    // Delete a User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
//    @GetMapping("/outsideuser/{id}")
//    public ArrayList<OutsideUser> getOutsideUserById(@PathVariable(value = "id") Long userId) {
//    	RestTemplate restTemplate = new RestTemplate();
//    	String fooResourceUrl = "https://jsonplaceholder.typicode.com/todos/";
//    	//List<OutsiderUser> list = new ;
//    	ResponseEntity<OutsideUser[]> response = restTemplate.getForEntity(fooResourceUrl, OutsideUser[].class);
//    	
//    	ArrayList<OutsideUser> list = new ArrayList<OutsideUser>();
//    	for(OutsideUser i: response.getBody()) {
//    		if(i.getUserId() == userId) {
//    			//System.out.println(i);
//    			list.add(i);
//    			User newUser = new User(i.getUserId(), i.getId(), i.getTitle(),  i.isCompleted());
//    			userRepository.save(newUser);
//    		}
//    	}
//    	
//    	
//    	return list;
//    }
    @GetMapping("/outsideuser/{userId}")
    public ArrayList<User> getOutsideUserById(@PathVariable(value = "userId") Long userId) {
    	
    	List<User> list = userRepository.findAll();
    	ArrayList<User> sendList = new ArrayList<User>();
    	for(User i: list) {
    		if(i.getUserId() == userId) {
    			sendList.add(i);
    		}
    	}
    	
    	
    	return sendList;
    }
    @GetMapping("/outsideuserbycompleted/{completed}")
    public ArrayList<OutsideUser> getOutsideUserByCompleted(@PathVariable(value = "completed") boolean completed) {
    	RestTemplate restTemplate = new RestTemplate();
    	String fooResourceUrl = "https://jsonplaceholder.typicode.com/todos/";
    	//List<OutsiderUser> list = new ;
    	ResponseEntity<OutsideUser[]> response = restTemplate.getForEntity(fooResourceUrl, OutsideUser[].class);
    	
    	ArrayList<OutsideUser> list = new ArrayList<OutsideUser>();
    	for(OutsideUser i: response.getBody()) {
    		if(i.isCompleted() == completed) {
    			//System.out.println(i);
    			list.add(i);
    			User newUser = new User(i.getUserId(), i.getId(), i.getTitle(),  i.isCompleted());
    			userRepository.save(newUser);
    		}
    	}
    	
    	
    	return list;
    }
    
}