package com.example.newShopApI.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

	public Product createProductService(Product product, MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			product.setProductIcon(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productRepository.save(product);
	}

	public Product updateProductService(Long productId, Product productDetails) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		product.setProductName(productDetails.getProductName());
		product.setProductDescription(productDetails.getProductDescription());
		product.setStock(productDetails.getStock());
		product.setProductIcon(productDetails.getProductIcon());

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
