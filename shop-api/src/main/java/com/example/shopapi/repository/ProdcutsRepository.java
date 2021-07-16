package com.example.shopapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;

@Repository
public interface ProdcutsRepository extends JpaRepository<Products, Long>,ProductsCustomRepository,
JpaSpecificationExecutor<Products>{
	List<Products> findAllProductsByProductCatagory(ProductCatagory productCatagory);
	List<Products> findAllProductsByName(String name);
}
