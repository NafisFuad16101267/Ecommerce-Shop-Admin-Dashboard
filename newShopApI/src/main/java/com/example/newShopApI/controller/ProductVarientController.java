package com.example.newShopApI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.newShopApI.model.ProductVarient;
import com.example.newShopApI.service.ProductVarientService;

@RestController
@RequestMapping("/api")
public class ProductVarientController {

	@Autowired
	ProductVarientService productVarientService;

	@GetMapping("/productVarients")
	public List<ProductVarient> getAllProductVarients() {
		return productVarientService.getAllProdutcVarientService();
	}

	@PostMapping("/productVarients")
	public ProductVarient createProductVarient(@Valid @RequestBody ProductVarient productVarient) {
		return productVarientService.createProductVarientService(productVarient);
	}

	@PutMapping("/productVarients/{id}")
	public ProductVarient updateProductVarient(@PathVariable(value = "id") Long productVarientId,
			@Valid @RequestBody ProductVarient productVarientDetails) {
		return productVarientService.updateProductVarientService(productVarientId, productVarientDetails);
	}

	@DeleteMapping("/productVarients/{id}")
	public ResponseEntity<?> deleteProductVarient(@PathVariable(value = "id") Long productId) {
		return productVarientService.deleteProductVarientService(productId);
	}
	
	@GetMapping("/productVarient/{varientName}")
	public List<ProductVarient> findProductVarientByName(@PathVariable(value = "varientName") String varientName) {
		return productVarientService.findProductVarientByNameService(varientName);
	}
	
	@GetMapping("/productVarient2/{varientName}")
	public ResponseEntity<?> findProductVarientByNameForm(@PathVariable(value = "varientName") String varientName) {
		ProductVarient productVarient = productVarientService.findProductVarientByNameService(varientName).get(0);
		Map res = new HashMap<>();
		res.put("id",productVarient.getId());
		res.put("varientName",productVarient.getVarientName());
		res.put("varientDescription",productVarient.getVarientDescription());
		res.put("price",productVarient.getPrice());
		res.put("stock",productVarient.getStock());
		res.put("productName",productVarient.getProduct().getProductName());
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/productVarientId/{id}")
	public ProductVarient findProductVarientByID(@PathVariable(value = "id") Long id) {
		return productVarientService.findProductVarientByIdService(id);
	}
}
