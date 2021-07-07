package com.example.shopapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.shopapi.exception.ResourceNotFoundException;
import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;
import com.example.shopapi.model.User;
import com.example.shopapi.repository.OrderRepository;
import com.example.shopapi.repository.PaymentRepository;
import com.example.shopapi.repository.ProdcutsCatagoryRepository;
import com.example.shopapi.repository.ProdcutsRepository;
import com.example.shopapi.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	ProdcutsRepository prodcutsRepository;
	@Autowired
	ProdcutsCatagoryRepository productCatagoryRepositroy;
	@Autowired
	OrderRepository orderRepositroy;
	@Autowired
	UserRepository userRepositroy;
	@Autowired
	PaymentRepository paymentRepository;

	
	public List<ProductCatagory> getAllProductCatagoryService() {
		return productCatagoryRepositroy.findAll();
	}

	public ProductCatagory updateupdatedProductCatagoryService(Long productCatagoryId, ProductCatagory productCatagoryDetails) {

		ProductCatagory productCatagory = productCatagoryRepositroy.findById(productCatagoryId)
                .orElseThrow(() -> new ResourceNotFoundException("productCatagory", "id", productCatagoryId));

		productCatagory.setCatagoryName(productCatagoryDetails.getCatagoryName());

        ProductCatagory updatedProductCatagory = productCatagoryRepositroy.save(productCatagory);
        return updatedProductCatagory;
	}
	
	public ResponseEntity<?> deleteProductCatagoryService(Long id) {
		ProductCatagory productCatagory = productCatagoryRepositroy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("productCatagory","id", id));
		
		List<Products> products = prodcutsRepository.findAllProductsByProductCatagory(productCatagory);
		
		for(Products prods : products) {
			prods.setProductCatagory(null);
		}
		
		productCatagoryRepositroy.delete(productCatagory);

        return ResponseEntity.ok().build();
    }

	public ProductCatagory createProductCatagoryService(ProductCatagory productCatagory) {
		return productCatagoryRepositroy.save(productCatagory);
	}
	
	public Products createNewProductService(Products products) {
		if (products.getProductCatagory() != null) {
			ProductCatagory productCatagory = productCatagoryRepositroy
					.findByCatagoryName(products.getProductCatagory().getCatagoryName());
			if (productCatagory == null) {
				createProductCatagoryService(products.getProductCatagory());
			}
		}
		return prodcutsRepository.save(products);
	}
	

	public List<Products> getAllProductService() {
		return prodcutsRepository.findAll();
	}
	
	public Products updateupdatedProductService(Long id, Products productDetails) {

		Products product = prodcutsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

		product.setName(productDetails.getName());
		product.setSize(productDetails.getSize());
		product.setColor(productDetails.getColor());
		product.setStock(productDetails.getStock());
		product.setProductCatagory(productDetails.getProductCatagory());

        Products updatedProduct= prodcutsRepository.save(product);
        return updatedProduct;
	}
	
	public ResponseEntity<?> deleteProductService(Long id){
		Products product = prodcutsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product","id", id));

		prodcutsRepository.delete(product);

        return ResponseEntity.ok().build();
	}
	
	public List<Order> getAllOrdersService() {
		return orderRepositroy.findAll();
	}
	
	public List<User> getAllUsersService() {
		return userRepositroy.findAll();
	}
}
