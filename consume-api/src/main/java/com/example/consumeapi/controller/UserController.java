package com.example.consumeapi.controller;

import com.example.consumeapi.exception.ResourceNotFoundException;
import com.example.consumeapi.model.OutsideUser;
import com.example.consumeapi.model.User;
import com.example.consumeapi.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.model.core.TypeRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
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

    // Get a Single Note
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "userId") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
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
    @GetMapping("/outsideuser/{id}")
    public ResponseEntity<?> getOutsideUserById(@PathVariable(value = "id") Long userId) {
    	RestTemplate restTemplate = new RestTemplate();
    	String fooResourceUrl = "https://jsonplaceholder.typicode.com/todos/";
//    	TypeRef<T, C> 
    	
    	ResponseEntity<> response = restTemplate.getForEntity(fooResourceUrl, List.class);
    	OutsideUser outsideUser = response.getBody();
    	
    	
    	for(OutsideUser i: response.getBody()) {
    		
    	}
    	return null;
    }
    
}