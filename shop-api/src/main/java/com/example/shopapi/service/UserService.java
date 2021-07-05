package com.example.shopapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;
import com.example.shopapi.repository.OrderRepository;
import com.example.shopapi.repository.ProdcutsCatagoryRepository;
import com.example.shopapi.repository.ProdcutsRepository;

@Service
public class UserService {
	@Autowired
	ProdcutsRepository prodcutsRepository;
	@Autowired
	ProdcutsCatagoryRepository productCatagoryRepositroy;
	@Autowired
	OrderRepository orderRepositroy;
	
	
	 public List<Products> getProductsByCriteriaService(String name,
				Long size,
				String color,
				Long stock,
				ProductCatagory prouctCatagory) {
		 //Products product = new Products(name, size, color, stock, prouctCatagory);
		 //System.out.println(product.getName());
		 return prodcutsRepository.findProductsByCatagory(name, color, size);
	    }
}
