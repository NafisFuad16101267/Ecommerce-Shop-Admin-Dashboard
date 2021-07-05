package com.example.shopapi.repository;

import java.util.List;

import com.example.shopapi.model.Products;

public interface ProductsCustomRepository{
	List<Products> findProductsByCatagory(String name, String color, Long size);
}
