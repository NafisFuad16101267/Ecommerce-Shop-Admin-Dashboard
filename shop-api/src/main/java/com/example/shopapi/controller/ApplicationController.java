package com.example.shopapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;


@Controller
public class ApplicationController {
	
	@Autowired
	AdminController adminController;
	
	@GetMapping("/index")
	public String goHome(){
		return "index";
	}
	
	@GetMapping("/newIndex")
	public ModelAndView getProducts(Map<String, Object> model) {
		List<Products> products = adminController.getAllProduct();
		List<Order> orders = adminController.getAllOrders();
		List<ProductCatagory> catagories = adminController.getAllProductCatagory();
		model.put("products",products);
		model.put("orders", orders);
		model.put("productCatagory", catagories);
		return new ModelAndView("index", model);
	}
	
	@GetMapping("/UpdateProducts")
	public ModelAndView updateProducts(Map<String, Object> model) {
		 
		return null;
	}
}


