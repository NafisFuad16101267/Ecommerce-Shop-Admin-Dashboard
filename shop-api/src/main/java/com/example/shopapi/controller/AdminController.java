package com.example.shopapi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.Products;
import com.example.shopapi.model.User;
import com.example.shopapi.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	@PostMapping("/productCatagory")
	public ProductCatagory createProductCatagory(@Valid @RequestBody @ModelAttribute("productCatagory") ProductCatagory productCatagory) {
		return adminService.createProductCatagoryService(productCatagory);
	}

	@GetMapping("/productCatagory")
	public List<ProductCatagory> getAllProductCatagory() {
		return adminService.getAllProductCatagoryService();
	}

	@PutMapping("/productCatagory/{id}")
	public ProductCatagory updateupdatedProductCatagory(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ProductCatagory productCatagoryDetails) {
		return adminService.updateupdatedProductCatagoryService(id, productCatagoryDetails);
	}

	@DeleteMapping("/productCatagory/{id}")
	public ResponseEntity<?> deleteProductCatagory(@PathVariable(value = "id") Long id) {
		return adminService.deleteProductCatagoryService(id);
	}

	@PostMapping("/product")
	public Products createNewProduct(@Valid @RequestBody @ModelAttribute("products") Products products) {
		return adminService.createNewProductService(products);
	}

	@GetMapping("/product")
	public List<Products> getAllProduct() {
		return adminService.getAllProductService();
	}

	@GetMapping("/")
	public ModelAndView getProducts(Map<String, Object> model) {
		List<Products> products = getAllProduct();
		model.put("products", products);
		return new ModelAndView("index", model);
	}

	@GetMapping("/createNewProd")
	public ModelAndView addNewProd(Map<String, Object> model) {
		return new ModelAndView("CreateNewProduct", model);
	}

	@PutMapping("/product/{id}")
	public Products updatedProduct(@PathVariable(value = "id") Long id, @Valid @RequestBody Products product) {
		return adminService.updateupdatedProductService(id, product);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
		return adminService.deleteProductService(id);
	}

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return adminService.getAllOrdersService();
	}

	@GetMapping("/seeAllOrders")
	public ModelAndView getOrders(Map<String, Object> model) {
		List<Order> orderList = getAllOrders();
		model.put("orders", orderList);
		return new ModelAndView("order", model);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return adminService.getAllUsersService();
	}
}
