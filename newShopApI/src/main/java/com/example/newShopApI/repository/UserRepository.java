package com.example.newShopApI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newShopApI.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
