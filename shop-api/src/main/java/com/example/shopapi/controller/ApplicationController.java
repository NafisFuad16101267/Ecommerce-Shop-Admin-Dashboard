package com.example.shopapi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.shopapi.model.Order;
import com.example.shopapi.model.ProductCatagory;
import com.example.shopapi.model.ProductPage;
import com.example.shopapi.model.ProductVarient;
import com.example.shopapi.model.Products;
import com.example.shopapi.model.User;
import com.example.shopapi.repository.ProdcutsCatagoryRepository;
import com.example.shopapi.repository.ProdcutsRepository;
import com.example.shopapi.repository.ProductVarientRepository;
import com.example.shopapi.service.AdminService;

@Controller
public class ApplicationController {

	@Autowired
	AdminController adminController;
	@Autowired
	ProdcutsRepository prodcutsRepository;
	@Autowired
	AdminService adminService;
	@Autowired
	ProdcutsCatagoryRepository prodcutsCatagoryRepository;
	@Autowired
	ProductVarientRepository productVarientRepository;

	@GetMapping("/index")
	public ModelAndView getProducts(Map<String, Object> model, ProductPage productpage) {
		List<Products> products = adminController.getAllProduct();
		List<Order> orders = adminController.getAllOrders();
		List<ProductCatagory> catagories = adminController.getAllProductCatagory();
		model.put("products", products);
		model.put("orders", orders);
		model.put("productCatagory", catagories);
		return new ModelAndView("index", model);
	}
	
	public Page<Products> getAllProductsService(ProductPage productpage) {
		Sort sort = Sort.by(productpage.getSortDirection(), productpage.getSortBy());
		Pageable pageable = PageRequest.of(productpage.getPageNumber(), 
				productpage.getPageSize(), sort);
		return prodcutsRepository.findAll(pageable);
	}

	@GetMapping("/UpdateProducts/{id}")
	public ModelAndView updateProducts(Map<String, Object> model, @PathVariable(value = "id") Long id) {
		Products product = prodcutsRepository.getById(id);
		model.put("product", product);
		return new ModelAndView("updateProducts", model);
	}

	@PostMapping("/UpdateProducts")
	public ModelAndView updateProductPage(Map<String, Object> model,
			@Valid @RequestBody @ModelAttribute("products") Products products) {
		Products savedproduct = adminService.createNewProductService(products);
		model.put("product", savedproduct);
		return new ModelAndView("updateProducts", model);
	}

	@GetMapping("/UpdateProductCategory/{id}")
	public ModelAndView updateProductCategory(Map<String, Object> model, @PathVariable(value = "id") Long id) {
		ProductCatagory productCatagoty = prodcutsCatagoryRepository.getById(id);
		model.put("productCatagoty", productCatagoty);
		return new ModelAndView("updateProductCatagory", model);
	}
	
	@PostMapping("/UpdateProductCategory")
	public ModelAndView updateProductCategoryPage(Map<String, Object> model, 
			@Valid @RequestBody @ModelAttribute("productCatagory") ProductCatagory productCatagory) {
		ProductCatagory savedProductCatagory = prodcutsCatagoryRepository.save(productCatagory);
		model.put("productCatagoty", savedProductCatagory);
		return new ModelAndView("updateProductCatagory", model);
	}

	@PostMapping("/Categories")
	public ModelAndView updateProductCatagoryPage(Map<String, Object> model,
			@Valid @RequestBody @ModelAttribute("productCatagory") ProductCatagory productCatagoty) {
		ProductCatagory savedproductCatagoty = adminService.createProductCatagoryService(productCatagoty);
		List<ProductCatagory> catagories = adminController.getAllProductCatagory();
		model.put("productCatagory", catagories);
		return new ModelAndView("categories", model);
	}

	@GetMapping("/Categories")
	public ModelAndView getCategories(Map<String, Object> model) {
		List<ProductCatagory> catagories = adminController.getAllProductCatagory();
		model.put("productCatagory", catagories);
		return new ModelAndView("categories", model);
	}

	@GetMapping("/productsList")
	public ModelAndView getProductsList(Map<String, Object> model){
		List<Products> products = adminController.getAllProduct();
		List<ProductCatagory> productCatagory = adminController.getAllProductCatagory();
		model.put("products", products);
		model.put("productCatagory", productCatagory);
		return new ModelAndView("products", model);
	}

	@GetMapping("/ordersList")
	public ModelAndView getOrdersList(Map<String, Object> model) {
		List<Order> orders = adminController.getAllOrders();
		model.put("orders", orders);
		return new ModelAndView("orders", model);
	}

	@GetMapping("/usersList")
	public ModelAndView getUsersList(Map<String, Object> model) {
		List<User> user = adminController.getAllUsers();
		model.put("user", user);
		return new ModelAndView("user", model);
	}
	
	@GetMapping("/variantList")
	public ModelAndView getVariantList(Map<String, Object> model) {
		List<ProductVarient> productVarients = adminController.getProductVarient();
		model.put("productVarient", productVarients);
		return new ModelAndView("productVarient", model);
	}
	
	@GetMapping("/UpdateProductVarient/{id}")
	public ModelAndView UpdateProductVarient(Map<String, Object> model, @PathVariable(value = "id") Long id) {
		ProductVarient productVarient = productVarientRepository.getById(id);
		model.put("productVarient", productVarient);
		return new ModelAndView("updateProductVarient", model);
	}
	
	@PostMapping("/UpdateProductVarient")
	public ModelAndView updateProductVarient(Map<String, Object> model,
			@Valid @RequestBody @ModelAttribute("products") ProductVarient productVarient) {
		return adminController.setProductVarient(productVarient);
	}
	
	@DeleteMapping("/ProductVarient/{id}")
	public ModelAndView deleteProductVarient(@PathVariable(value = "id") Long id) {
		productVarientRepository.deleteById(id);
		return new ModelAndView("redirect:/variantList");
	}
	
	@GetMapping("/sales")
	public ModelAndView showSalesReport(Map<String, Object> model) {
		List<Order> orders = adminController.getAllOrders();
		long totalRevenue = 0;
		for(Order order : orders) {
			totalRevenue = totalRevenue + order.getTotalPrice();
		}
		
		List<Products> products = adminController.getAllProduct();
		int mostOrderd = 0;
		Products mostSoldProducts = null;
		for(Products prod : products) {
			if(prod.getOrders().size() > mostOrderd) {
				mostSoldProducts = prod;
				mostOrderd = prod.getOrders().size(); 
			}
		}
		
		model.put("totalRevenue", totalRevenue);
		model.put("totalOrders", orders.size());
		model.put("mostSoldProducts",mostSoldProducts);
		model.put("mostOrderd", mostOrderd);
		return new ModelAndView("sales",model);
	}
}
