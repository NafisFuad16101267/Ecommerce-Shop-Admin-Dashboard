package com.example.newShopApI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newShopApI.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	List<ProductCategory> findAllByCategoryName(String categoryName);
}
