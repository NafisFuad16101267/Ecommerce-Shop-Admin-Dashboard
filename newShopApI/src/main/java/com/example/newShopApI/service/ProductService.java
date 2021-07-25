package com.example.newShopApI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.Product;
import com.example.newShopApI.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProdutcsService() {
		return productRepository.findAll();
	}

	public Product createProductService(Product product) {
		return productRepository.save(product);
	}

	public Product updateProductService(Long productId, Product productDetails) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		product.setProductName(productDetails.getProductName());
		product.setProductDescription(productDetails.getProductDescription());

		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	public ResponseEntity<?> deleteProductService(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		productRepository.delete(product);

		return ResponseEntity.ok().build();
	}

	public Product findProductByIdService(long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		return product;
	}

	public List<Product> findProductByName(String productName) {
		return productRepository.findAllByProductName(productName);
	}
}
