package com.example.consumeapi.repository;

import com.example.consumeapi.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByUserId(Long userId);
	List<User> findAllByCompleted(Boolean completed);
	List<User> findAllByUserIdAndCompleted(Long userId, Boolean completed);
}