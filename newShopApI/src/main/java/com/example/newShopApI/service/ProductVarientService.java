package com.example.newShopApI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.Product;
import com.example.newShopApI.model.ProductVarient;
import com.example.newShopApI.repository.ProductVarientRepository;

@Service
public class ProductVarientService {

	@Autowired
	ProductVarientRepository productVarientRepository;
	@Autowired
	ProductService productService;

	public List<ProductVarient> getAllProdutcVarientService() {
		return productVarientRepository.findAll();
	}

	public ProductVarient createProductVarientService(ProductVarient productVarient) {
		ProductVarient savedProductVarient = productVarientRepository.save(productVarient);
		stockManagement(savedProductVarient);
		return savedProductVarient;
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
		if(productVarientDetails.getStock() != null)
			productVarient.setStock(productVarientDetails.getStock());
		
		ProductVarient UpdatedproductVarient = productVarientRepository.save(productVarient);
		stockManagement(UpdatedproductVarient);
		return UpdatedproductVarient;
	}

	public ResponseEntity<?> deleteProductVarientService(Long productVarientId) {
		ProductVarient productVarient = productVarientRepository.findById(productVarientId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductVarient", "id", productVarientId));

		productVarientRepository.delete(productVarient);
		stockManagement(productVarient);

		return ResponseEntity.ok().build();
	}

	public ProductVarient findProductVarientByIdService(Long productVarientId) {
		ProductVarient productVarient = productVarientRepository.findById(productVarientId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductVarient", "id", productVarientId));
		return productVarient;
	}
	
	public List<ProductVarient> findProductVarientByNameService(String varientName) {
		return productVarientRepository.findAllByVarientName(varientName);
	}
	
	public void stockManagement(ProductVarient productVarient) {
		long totalStock = 0;
		Product product = productVarient.getProduct();
		for(ProductVarient prodVar : product.getProductVarient()) {
			totalStock = totalStock + prodVar.getStock();
		}
		product.setStock(totalStock);
		productService.updateProductService(product.getId(), product);
	}
}
