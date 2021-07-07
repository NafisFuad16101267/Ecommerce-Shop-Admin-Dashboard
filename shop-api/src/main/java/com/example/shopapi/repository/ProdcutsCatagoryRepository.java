package com.example.shopapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopapi.model.ProductCatagory;



@Repository
public interface ProdcutsCatagoryRepository extends JpaRepository<ProductCatagory, Long> {
	ProductCatagory findByCatagoryName(String catagoryName);
}
