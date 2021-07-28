package com.example.newShopApI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.newShopApI.exception.ResourceNotFoundException;
import com.example.newShopApI.model.Order;
import com.example.newShopApI.model.Product;
import com.example.newShopApI.model.ProductCategory;
import com.example.newShopApI.model.ProductVarient;
import com.example.newShopApI.model.User;
import com.example.newShopApI.service.OrderService;
import com.example.newShopApI.service.ProductCategoryService;
import com.example.newShopApI.service.ProductService;
import com.example.newShopApI.service.ProductVarientService;
import com.example.newShopApI.service.UserService;

@RestController
public class ApplicationController {

	@Autowired
	ProductService productService;
	@Autowired
	ProductCategoryService productCategoryService;
	@Autowired
	ProductVarientService productVarientService;
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;

	@GetMapping("/index")
	public ModelAndView indexPage(Map<String, Object> model) {
		return new ModelAndView("index", model);
	}

	// Product Category View Controller

	@GetMapping("/productCategories")
	public ModelAndView productCategoryPage(Map<String, Object> model) {
		List<ProductCategory> productCategory = productCategoryService.getAllProductCategoriesService();
		model.put("productCategory", productCategory);
		return new ModelAndView("categories", model);
	}

	@PostMapping("/admin/productCategory")
	public ModelAndView createNewProductCategory(
			@Valid @RequestBody @ModelAttribute("productCategory") ProductCategory productCategory) {
		productCategoryService.createProductCategoryService(productCategory);
		return new ModelAndView("redirect:/productCategories");
	}

	@DeleteMapping("/admin/productCategory/{id}")
	public ModelAndView deleteProductCategory(@PathVariable(value = "id") Long productCategoryId) {
		productCategoryService.deleteProductCategotyService(productCategoryId);
		return new ModelAndView("redirect:/productCategories");
	}

	@GetMapping("/admin/productCategory/{id}")
	public ModelAndView updateproductCategory(@PathVariable(value = "id") Long productCategoryId,
			Map<String, Object> model) {
		ProductCategory productCategory = productCategoryService.findProductCategoryById(productCategoryId);
		model.put("productCategory", productCategory);
		return new ModelAndView("updateProductCatagory", model);
	}

	@PostMapping("/admin/UpdateproductCategory")
	public ModelAndView updateproductCategoryView(
			@Valid @RequestBody @ModelAttribute("productCategory") ProductCategory productCategoryDetails) {
		productCategoryService.updateProductCategoryService(productCategoryDetails.getId(), productCategoryDetails);
		return new ModelAndView("redirect:/productCategories");
	}

	// Product View Controllers

	@GetMapping("/products")
	public ModelAndView productPage(Map<String, Object> model) {
		List<Product> products = productService.getAllProdutcsService();
		List<ProductCategory> productCategory = productCategoryService.getAllProductCategoriesService();
		model.put("products", products);
		model.put("productCategory", productCategory);
		return new ModelAndView("products", model);
	}

	@PostMapping("/admin/product")
	public ModelAndView createNewProduct(@Valid @RequestBody @ModelAttribute("product") Product product,
			@Valid @RequestBody @ModelAttribute("categoryName") String categoryName, Map<String, Object> model) {
		List<ProductCategory> productCategory = productCategoryService.searchByName(categoryName);
		if (productCategory.size() > 0) {
			product.setProductCategory(productCategory.get(0));
			ProductVarient productVarient = new ProductVarient();
			productVarient.setProduct(product);
			productVarient.setVarientName(product.getProductName());
			productVarient.setVarientDescription(product.getProductDescription());
			productVarient.setStock(product.getStock());
			productVarient.setPrice(1.0);
			List<ProductVarient> productVarients = new ArrayList<>();
			productVarients.add(productVarient);
			product.setProductVarient(productVarients);
			productService.createProductService(product);
			return new ModelAndView("redirect:/products");
		} else {
			String errorMessage = "Please Enter a Product Category form suggestion";
			model.put("errorMessage", errorMessage);
			return new ModelAndView("error", model);
		}
	}

	@DeleteMapping("/admin/product/{id}")
	public ModelAndView deleteProduct(@PathVariable(value = "id") Long productId) {
		productService.deleteProductService(productId);
		return new ModelAndView("redirect:/products");
	}

	@GetMapping("/admin/product/{id}")
	public ModelAndView updateProduct(@PathVariable(value = "id") Long productId, Map<String, Object> model) {
		Product product = productService.findProductByIdService(productId);
		List<ProductCategory> prouctCategories = productCategoryService.getAllProductCategoriesService();
		model.put("product", product);
		model.put("prouctCategories", prouctCategories);
		return new ModelAndView("updateProducts", model);
	}

	@PostMapping("/admin/UpdateProduct")
	public ModelAndView updateProductView(@Valid @RequestBody @ModelAttribute("product") Product productDetails,
			@Valid @RequestBody @ModelAttribute("categoryName") String categoryName, Map<String, Object> model) {
		Product product = productService.findProductByIdService(productDetails.getId());
		if (productDetails.getProductName() != null)
			product.setProductName(productDetails.getProductName());
		if (productDetails.getProductDescription() != null)
			product.setProductDescription(productDetails.getProductDescription());
		if (productDetails.getStock() != null)
			product.setStock(productDetails.getStock());
		List<ProductCategory> productCategory = productCategoryService.searchByName(categoryName);
		if (productCategory.size() > 0) {
			product.setProductCategory(productCategory.get(0));
			productService.updateProductService(productDetails.getId(), product);
			return new ModelAndView("redirect:/products");
		} else {
			String errorMessage = "Please Enter a Product Category form suggestion";
			model.put("errorMessage", errorMessage);
			return new ModelAndView("error", model);
		}
	}

	// Product Varient View Controller

	@GetMapping("/productVarients")
	public ModelAndView productVarientsPage(Map<String, Object> model) {
		List<ProductVarient> productVarient = productVarientService.getAllProdutcVarientService();
		List<Product> products = productService.getAllProdutcsService();
		model.put("productVarient", productVarient);
		model.put("products", products);
		return new ModelAndView("productVarient", model);
	}

	@PostMapping("/admin/productVarient")
	public ModelAndView createNewProductVarient(
			@Valid @RequestBody @ModelAttribute("productVarient") ProductVarient productVarient,
			@Valid @RequestBody @ModelAttribute("productName") String productName, Map<String, Object> model) {
		List<Product> product = productService.findProductByName(productName);
		if (product.size() > 0) {
			Product inputProduct = product.get(0);
			if (inputProduct.getProductVarient().size() > 0) {
				ProductVarient savedProductVarient = inputProduct.getProductVarient().get(0);
				if (savedProductVarient.getVarientName().equals(inputProduct.getProductName())) {
					inputProduct.setStock(
							inputProduct.getStock() - savedProductVarient.getStock() + productVarient.getStock());
					inputProduct.getProductVarient().remove(0);
				}
			}
			productVarient.setProduct(product.get(0));
		} else {
			String errorMessage = "Please enter a Product Name form the Suggestions";
			model.put("errorMessage", errorMessage);
			return new ModelAndView("error", model);
		}
		productVarientService.createProductVarientService(productVarient);
		return new ModelAndView("redirect:/productVarients");
	}

	@GetMapping("/admin/productVarient/{id}")
	public ModelAndView updateproductVarient(@PathVariable(value = "id") Long productVarientId,
			Map<String, Object> model) {
		ProductVarient productVarient = productVarientService.findProductVarientByIdService(productVarientId);
		model.put("productVarient", productVarient);
		return new ModelAndView("updateProductVarient", model);
	}

	@PostMapping("/admin/UpdateProductVarinet")
	public ModelAndView updateProductVarientView(
			@Valid @RequestBody @ModelAttribute("productVarient") ProductVarient productVarientDetails,
			@Valid @RequestBody @ModelAttribute("productName") String productName, Map<String, Object> model) {
		ProductVarient productVarinet = productVarientService
				.findProductVarientByIdService(productVarientDetails.getId());

		List<Product> product = productService.findProductByName(productName);
		if (product.size() < 1)
			throw new ResourceNotFoundException("productName", "productName", productName);

		if (productVarientDetails.getVarientName() != null)
			productVarinet.setVarientName(productVarientDetails.getVarientName());
		if (productVarientDetails.getVarientDescription() != null)
			productVarinet.setVarientDescription(productVarientDetails.getVarientDescription());
		if (productVarientDetails.getPrice() != null)
			productVarinet.setPrice(productVarientDetails.getPrice());
		if (productVarientDetails.getStock() != null)
			productVarinet.setStock(productVarientDetails.getStock());
		if (product.size() > 0)
			productVarinet.setProduct(product.get(0));
		else {
			String errorMessage = "Please enter a Product Name form the Suggestions";
			model.put("errorMessage", errorMessage);
			return new ModelAndView("error", model);
		}

		productVarientService.updateProductVarientService(productVarientDetails.getId(), productVarinet);
		return new ModelAndView("redirect:/productVarients");
	}

	@DeleteMapping("/admin/productVarient/{id}")
	public ModelAndView deleteProductVarient(@PathVariable(value = "id") Long productVarientId) {
		productVarientService.deleteProductVarientService(productVarientId);
		return new ModelAndView("redirect:/productVarients");
	}

	// Order View Controller

	@GetMapping("/orders")
	public ModelAndView orderPage(Map<String, Object> model) {
		List<Order> orders = orderService.getAllOrdersService();
		model.put("orders", orders);
		return new ModelAndView("orders", model);
	}

	@DeleteMapping("/admin/order/{id}")
	public ModelAndView deleteOrder(@PathVariable Long id) {
		orderService.deleteOrderService(id);
		return new ModelAndView("redirect:/orders");
	}

	// User View Controller

	@GetMapping("/users")
	public ModelAndView userPage(Map<String, Object> model) {
		List<User> user = userService.getAllUserService();
		model.put("user", user);
		return new ModelAndView("user", model);
	}

	// Sales View Controller
	@GetMapping("/sales")
	public ModelAndView salesPage(Map<String, Object> model) {
		List<Order> orders = orderService.getAllOrdersService();
		Double totalRevenue = 0.0;
		for (Order order : orders) {
			totalRevenue = totalRevenue + order.getTotalPrice();
		}
		List<Product> products = productService.getAllProdutcsService();
		int orderNumber = 0;
		Product mostSoldProducts = new Product();
		for (Product product : products) {
			int productOrdered = 0;
			List<ProductVarient> productVarients = product.getProductVarient();
			for (ProductVarient productVarient : productVarients) {
				List<Order> varientOrders = productVarient.getOrders();
				productOrdered = productOrdered + varientOrders.size();
			}
			if (productOrdered > orderNumber) {
				orderNumber = productOrdered;
				mostSoldProducts = product;
			}
		}
		model.put("totalRevenue", totalRevenue);
		model.put("totalOrders", orders.size());
		model.put("mostSoldProducts", mostSoldProducts);
		model.put("mostOrderd", orderNumber);
		return new ModelAndView("sales", model);
	}

}
