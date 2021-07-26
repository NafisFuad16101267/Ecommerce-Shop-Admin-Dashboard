package com.example.newShopApI.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Product_Varient")
@Table(name = "Product_Varient")
public class ProductVarient {
	@Id
	@SequenceGenerator(name = "productVarient_sequence", sequenceName = "productVarient_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "productVarient_sequence")
	@Column(name = "id", updatable = false)
	private long id;

	@Column(name = "productvarient_name", nullable = false, columnDefinition = "TEXT")
	private String varientName;

	@Column(name = "productvarient_description", nullable = false, columnDefinition = "TEXT")
	private String varientDescription;

	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "stock", nullable = false)
	private Long stock;

	@ManyToOne
	@JsonIgnore
	private Product product;

	@ManyToMany(mappedBy = "productVarients", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Order> orders;
	
	public ProductVarient() {
		
	}

	public ProductVarient(String varientName, String varientDescription, Double price, Product product,Long stock,
			List<Order> orders) {
		super();
		this.varientName = varientName;
		this.varientDescription = varientDescription;
		this.price = price;
		this.stock = stock;
		this.product = product;
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVarientName() {
		return varientName;
	}

	public void setVarientName(String varientName) {
		this.varientName = varientName;
	}

	public String getVarientDescription() {
		return varientDescription;
	}

	public void setVarientDescription(String varientDescription) {
		this.varientDescription = varientDescription;
	}
	
	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
