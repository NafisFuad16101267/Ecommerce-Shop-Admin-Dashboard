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

import com.example.newShopApI.model.ProductCategory;
import com.example.newShopApI.service.ProductCategoryService;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

	@Autowired
	ProductCategoryService productCategoryService;

	@GetMapping("/productCategories")
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryService.getAllProductCategoriesService();
	}

	@PostMapping("/productCategories")
	public ProductCategory createProductCategory(@Valid @RequestBody ProductCategory productCategory) {
		return productCategoryService.createProductCategoryService(productCategory);
	}

	@PutMapping("/productCategories/{id}")
	public ProductCategory updateProductCategory(@PathVariable(value = "id") Long productCategoryId,
			@Valid @RequestBody ProductCategory productCategoryDetails) {
		return productCategoryService.updateProductCategoryService(productCategoryId, productCategoryDetails);
	}

	@DeleteMapping("/productCategories/{id}")
	public ResponseEntity<?> deleteProductCategoty(@PathVariable(value = "id") Long productCategoryId) {
		return productCategoryService.deleteProductCategotyService(productCategoryId);
	}
}
