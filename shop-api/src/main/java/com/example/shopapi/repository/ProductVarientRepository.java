package com.example.shopapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductVarient;

public interface ProductVarientRepository extends JpaRepository<ProductVarient, Long>,
JpaSpecificationExecutor<ProductVarient>{
	List<ProductVarient> findAllByVarientName(String name);
}
