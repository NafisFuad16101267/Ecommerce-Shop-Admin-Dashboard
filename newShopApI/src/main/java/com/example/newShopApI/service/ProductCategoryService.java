package com.example.newShopApI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.ProductCategory;
import com.example.newShopApI.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	public List<ProductCategory> getAllProductCategoriesService() {
		return productCategoryRepository.findAll();
	}

	public ProductCategory createProductCategoryService(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	public ProductCategory updateProductCategoryService(Long productCategoryId,
			ProductCategory productCategoryDetails) {

		ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("productCategory", "id", productCategoryId));

		productCategory.setCategoryName(productCategoryDetails.getCategoryName());
		productCategory.setCategoryActivity(productCategoryDetails.getCategoryActivity());

		ProductCategory updatedProductCategory = productCategoryRepository.save(productCategory);
		return updatedProductCategory;
	}

	public ResponseEntity<?> deleteProductCategotyService(Long productCategoryId) {
		ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("productCategory", "id", productCategoryId));

		productCategoryRepository.delete(productCategory);

		return ResponseEntity.ok().build();
	}
	
	public List<ProductCategory> searchByName(String name) {
		return productCategoryRepository.findAllByCategoryName(name);
	}
	
	public ProductCategory findProductCategoryById(long productCategoryId) {
		return productCategoryRepository.getById(productCategoryId);
	}

}
