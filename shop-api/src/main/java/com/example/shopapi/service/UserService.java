package com.example.shopapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.shopapi.exception.AddingProductToPaidOrderException;
import com.example.shopapi.exception.PaidOrderException;
import com.example.shopapi.exception.ResourceNotFoundException;
import com.example.shopapi.model.Order;
import com.example.shopapi.model.Payment;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.ProductPage;
import com.example.shopapi.model.ProductVarient;
import com.example.shopapi.model.Products;
import com.example.shopapi.model.User;
import com.example.shopapi.repository.OrderRepository;
import com.example.shopapi.repository.PaymentRepository;
import com.example.shopapi.repository.ProdcutsCatagoryRepository;
import com.example.shopapi.repository.ProdcutsRepository;
import com.example.shopapi.repository.ProductVarientRepository;
import com.example.shopapi.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	ProdcutsRepository prodcutsRepository;
	@Autowired
	ProdcutsCatagoryRepository productCatagoryRepositroy;
	@Autowired
	OrderRepository orderRepositroy;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ProductVarientRepository productVarientRepository;
	
	public User resgisterUserService(String userName, String userEmail, 
			Boolean userType) {
		User newUser = new User();
		newUser.setUserName(userName);
		newUser.setUserEmail(userEmail);
		newUser.setUserType(userType);
		return userRepository.save(newUser);
	}

	public List<Products> getProductsByCriteriaService(String name, Long size, String color, Long stock,
			ProductCatagory prouctCatagory) {
		return prodcutsRepository.findProductsByCatagory(name, color, size);
	}

	public Page<Products> getAllProductsService(ProductPage productpage) {
		Sort sort = Sort.by(productpage.getSortDirection(), productpage.getSortBy());
		Pageable pageable = PageRequest.of(productpage.getPageNumber(), 
				productpage.getPageSize(), sort);
		return prodcutsRepository.findAll(pageable);
	}
	
	public List<Products> getAllProductsByName(String name){
			System.out.println(name);
			return prodcutsRepository.findAllProductsByName(name);
	}
	
	public Order orderProductService(ArrayList<Long> id, Long userId){
		Order newOrder = new Order();
		Long totalprice = (long) 0;
		ArrayList<Products> productsList = new ArrayList<>();
		for(Long prodId : id) {
			Optional<Products> newProducts = prodcutsRepository.findById(prodId);
			if(newProducts.isPresent()) {
				productsList.add(newProducts.get());
				totalprice = totalprice + newProducts.get().getPrice(); 
			}
		}
		newOrder.setProducts(productsList);
		newOrder.setTotalPrice(totalprice);
		
		Optional<User> newUser = userRepository.findById(userId);
		if(newUser.isPresent())
			newOrder.setUser(newUser.get());
		
		return orderRepositroy.save(newOrder);
	}
	
	public Order orderAddProductService(ArrayList<Long> id, Long orderId){
		Order savedOrder = orderRepositroy.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("id", "id", orderId));
		if(savedOrder.getPayment() == null) {
			long total = savedOrder.getTotalPrice();
			List<Products> productsList = savedOrder.getProducts();
			for(Long prodId : id) {
				Optional<Products> newProducts = prodcutsRepository.findById(prodId);
				if(newProducts.isPresent()) {
					productsList.add(newProducts.get());
					total = total + newProducts.get().getPrice();
				}
			}
			savedOrder.setProducts(productsList);
			savedOrder.setTotalPrice(total);
			return orderRepositroy.save(savedOrder);
		} else {
			throw new AddingProductToPaidOrderException();
			//return null;
		}
	}
	
	public Order userPaymentService(Long orderId, String paymentOption, Long transactionId) {
		Order savedOrder = orderRepositroy.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("id", "id", orderId));
		Payment payment = new Payment();
		payment.setPaymentType(paymentOption);
		payment.setCompleted(true);
		payment.setTransactionId(transactionId);
		payment.setOrder(savedOrder);
		Payment savedPayment = paymentRepository.save(payment);
		return savedPayment.getOrder();
	}
	
	public ResponseEntity<?> deleteOrderService(Long orderId){
		Order savedOrder = orderRepositroy.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("id", "id", orderId));
		if(savedOrder.getPayment() == null) {
			orderRepositroy.delete(savedOrder);
		}else {
			throw new PaidOrderException();
		}
		return null;
	}
	public List<Products> filterProductByVarientService(String name){
		List<ProductVarient> list = productVarientRepository.findAllByVarientName(name);
		ArrayList<Products> prods = new ArrayList();
		for(ProductVarient varient : list) {
			Products prod = varient.getProduct();
			prods.add(prod);
		}
		return prods;
	}
}
