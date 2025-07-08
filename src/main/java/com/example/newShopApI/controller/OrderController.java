package com.example.newShopApI.controller;

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

import com.example.newShopApI.model.Order;
import com.example.newShopApI.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrdersService();
	}
	
	@PostMapping("/orders")
	public Order createNewOrder(@RequestParam @PathVariable(value = "id") ArrayList<Long> id,
			@RequestParam @PathVariable(value = "userId") Long userId) {
		return orderService.createNewOrderService(id, userId);
	}
	
	@PutMapping("/orders/{id}")
	public Order updateOrder(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order order) {
		return orderService.updateOrderService(orderId, order);
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
		return orderService.deleteOrderService(orderId);
	}
}
