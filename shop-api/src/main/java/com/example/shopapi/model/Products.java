package com.example.shopapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long price;
	private String name;
	private Long size;
	private String color;
	private Long stock;
	@ManyToOne
	private ProductCatagory productCatagory;
	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private List<Order> orders;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Products() {

	}
	
	public Products(String name, Long size, String color, Long stock, ProductCatagory productCatagory) {
		this.name = name;
		this.size = size;
		this.color = color;
		this.stock = stock;
		this.productCatagory = productCatagory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
