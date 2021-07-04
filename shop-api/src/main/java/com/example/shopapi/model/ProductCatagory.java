package com.example.shopapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductCatagory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String catagoryName;
	@OneToMany(mappedBy = "productCatagory")
	private List<Products> products;

	public ProductCatagory() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

}
