package com.example.shopapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long size;
	private String color;
	private Long stock;
	@ManyToOne
	private ProductCatagory productCatagory;
	@ManyToMany(mappedBy = "products")
	private List<Order> orders;

	public Products() {

	}

	public Long getProductId() {
		return id;
	}

	public void setProductId(Long id) {
		this.id = id;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public ProductCatagory getProductCatagory() {
		return productCatagory;
	}

	public void setProductCatagory(ProductCatagory productCatagory) {
		this.productCatagory = productCatagory;
	}
}
