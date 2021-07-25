package com.example.newShopApI.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.newShopApI.model.Product;
import com.example.newShopApI.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public List<Product> getAllProdutcs() {
		return productService.getAllProdutcsService();
	}

	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productService.createProductService(product);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) {
		return productService.updateProductService(productId, productDetails);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		return productService.deleteProductService(productId);
	}
}
