package com.example.shopapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopapi.model.Products;

@Repository
public interface ProdcutsRepository extends JpaRepository<Products, Long> {
	
}
