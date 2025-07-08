package com.example.newShopApI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newShopApI.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByProductName(String name);
}
