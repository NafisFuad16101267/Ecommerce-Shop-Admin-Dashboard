package com.example.newShopApI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.ProductVarient;
import com.example.newShopApI.repository.ProductVarientRepository;

@Service
public class ProductVarientService {

	@Autowired
	ProductVarientRepository productVarientRepository;

	public List<ProductVarient> getAllProdutcVarientService() {
		return productVarientRepository.findAll();
	}

	public ProductVarient createProductVarientService(ProductVarient productVarient) {
		return productVarientRepository.save(productVarient);
	}

	public ProductVarient updateProductVarientService(Long productVarientId, ProductVarient productVarientDetails) {
		ProductVarient productVarient = productVarientRepository.findById(productVarientId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductVarient", "id", productVarientId));

		if (productVarientDetails.getVarientName() != null)
			productVarient.setVarientName(productVarientDetails.getVarientName());
		if (productVarientDetails.getVarientDescription() != null)
			productVarient.setVarientDescription(productVarientDetails.getVarientDescription());
		if (productVarientDetails.getPrice() != null)
			productVarient.setPrice(productVarientDetails.getPrice());

		ProductVarient UpdatedproductVarient = productVarientRepository.save(productVarient);
		return UpdatedproductVarient;
	}

	public ResponseEntity<?> deleteProductVarientService(Long productVarientId) {
		ProductVarient productVarient = productVarientRepository.findById(productVarientId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductVarient", "id", productVarientId));

		productVarientRepository.delete(productVarient);

		return ResponseEntity.ok().build();
	}

	public ProductVarient findProductVarientByIdService(Long productVarientId) {
		ProductVarient productVarient = productVarientRepository.findById(productVarientId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductVarient", "id", productVarientId));
		return productVarient;
	}
}
