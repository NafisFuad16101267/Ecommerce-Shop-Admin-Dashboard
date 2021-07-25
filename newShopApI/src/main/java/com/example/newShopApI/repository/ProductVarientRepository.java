package com.example.newShopApI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newShopApI.model.ProductVarient;

public interface ProductVarientRepository extends JpaRepository<ProductVarient, Long>{
	List<ProductVarient> findAllByVarientName(String varientName);
}
