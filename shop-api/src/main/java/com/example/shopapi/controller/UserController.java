package com.example.shopapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;
import com.example.shopapi.model.User;
import com.example.shopapi.repository.ProdcutsRepository;
import com.example.shopapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ProdcutsRepository prodcutsRepository;
	
	@PostMapping("/register")
	public User resgisterUser(@Valid @RequestBody User user) {
		return userService.resgisterUserService(user);
	}

	@GetMapping("/products/filter")
	public List<Products> getProductsByCriteria(@RequestParam(defaultValue = "null") String name,
			@RequestParam(required = false) Long size, @RequestParam(defaultValue = "null") String color,
			@RequestParam(defaultValue = "-1") Long stock,
			@RequestParam(required = false) ProductCatagory prouctCatagory) {
		return userService.getProductsByCriteriaService(name, size, color, stock, prouctCatagory);
	}

	@GetMapping("/products")
	public List<Products> getAllprodcuts() {
		return userService.getAllProductsService();
	}
	@GetMapping("/products/byname")
	public List<Products> getAllprodcutsByName(@RequestParam String name) {
		return userService.getAllProductsByName(name);
	}
	
	@PostMapping("/order")
	public Order orderProduct(@RequestParam @PathVariable(value = "id") ArrayList<Long> id,
			@RequestParam @PathVariable(value = "userId") Long userId){
		return userService.orderProductService(id, userId);
	}
	
	@PutMapping("/order")
	public Order orderAddProduct(@RequestParam @PathVariable(value = "id") ArrayList<Long> id,
			@RequestParam @PathVariable(value = "orderId") Long orderId){
		return userService.orderAddProductService(id, orderId);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long orderId) {
		return userService.deleteOrderService(orderId);
	}
	
	@PostMapping("/payment")
	public Order userPayment(@RequestParam Long orderId,
		@RequestParam String paymentOption,
		@RequestParam Long transactionId){
		return userService.userPaymentService(orderId,paymentOption,transactionId);
	}
}
