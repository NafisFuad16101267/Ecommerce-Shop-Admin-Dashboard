package com.example.newShopApI.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "totalPrice", nullable = false)
	private Double totalPrice;

	@ManyToMany
	private List<ProductVarient> productVarients;

	public Order() {

	}

	public Order(Double totalPrice, List<ProductVarient> productVarients) {
		super();
		this.totalPrice = totalPrice;
		this.productVarients = productVarients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ProductVarient> getProductVarients() {
		return productVarients;
	}

	public void setProductVarients(List<ProductVarient> productVarients) {
		this.productVarients = productVarients;
	}

}
