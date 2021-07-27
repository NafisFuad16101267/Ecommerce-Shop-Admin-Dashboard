package com.example.newShopApI.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.Order;
import com.example.newShopApI.model.Payment;
import com.example.newShopApI.repository.PaymentRepository;
import com.example.newShopApI.service.OrderService;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	OrderService orderService;

	@PostMapping("/payment/{orderID}")
	public Payment makePayment(@PathVariable(value="orderID") Long orderId,
			@Valid @RequestBody Payment paymentDetails) {
		Order order = orderService.findOrderById(orderId);
		paymentDetails.setOrder(order);
		if (paymentDetails.getPaymentType() == null)
			throw new ResourceNotFoundException(paymentDetails.getPaymentType(), "paymentType", paymentDetails);
		if (paymentDetails.getTransactionID() == null)
			throw new ResourceNotFoundException(paymentDetails.getTransactionID(), "TransactionId", paymentDetails);
		Payment savedPayment=  paymentRepository.save(paymentDetails);
		order.setPayment(savedPayment);
		orderService.updateOrderService(orderId, order);
		return savedPayment;
	}
}
