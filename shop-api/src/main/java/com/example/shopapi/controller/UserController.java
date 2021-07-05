package com.example.shopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;
import com.example.shopapi.repository.ProdcutsRepository;
import com.example.shopapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ProdcutsRepository prodcutsRepository;
	
	@GetMapping("/users/filter")
	public List<Products> getProductsByCriteria(@RequestParam(defaultValue = "null") String name,
			@RequestParam(required=false) Long size,
			@RequestParam(defaultValue = "null") String color,
			@RequestParam(defaultValue = "-1") Long stock,
			@RequestParam(required=false) ProductCatagory prouctCatagory){
		return userService.getProductsByCriteriaService(name,size,color,stock,prouctCatagory);
//		return null;
	}
}
