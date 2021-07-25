package com.example.newShopApI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.Order;
import com.example.newShopApI.model.ProductVarient;
import com.example.newShopApI.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductVarientService productVarientService;
	@Autowired
	UserService userService;

	public List<Order> getAllOrdersService() {
		return orderRepository.findAll();
	}

	public Order createNewOrderService(ArrayList<Long> ids, Long userId) {
		double totalPrice = 0.0;
		List<ProductVarient> productVarients = new ArrayList<ProductVarient>();
		for(Long id : ids) {
			ProductVarient productVarient = productVarientService.findProductVarientByIdService(id);
			productVarients.add(productVarient);
			totalPrice = totalPrice + productVarient.getPrice();
		}
		
		Order order = new Order();
		order.setProductVarients(productVarients);
		order.setUser(userService.findUserById(userId));
		order.setTotalPrice(totalPrice);
		
		return orderRepository.save(order);
	}

	public Order updateOrderService(Long orderId, Order orderDetails) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
		if (orderDetails.getProductVarients() != null)
			order.setProductVarients(orderDetails.getProductVarients());

		List<ProductVarient> productVarients = order.getProductVarients();
		double totalPrice = 0.0;
		for (ProductVarient productVarient : productVarients) {
			totalPrice = totalPrice + productVarient.getPrice();
		}
		order.setTotalPrice(totalPrice);
		
		return orderRepository.save(order);
	}
	
	public ResponseEntity<?> deleteOrderService(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
		
		orderRepository.delete(order);
		
		return ResponseEntity.ok().build();
	}
}
