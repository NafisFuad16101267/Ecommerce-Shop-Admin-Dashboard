package com.example.shopapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductVarient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String varientName;
	private String varientDetails;
	@ManyToOne
	private Products product;
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
	public String getVarientDetails() {
		return varientDetails;
	}
	public void setVarientDetails(String varientDetails) {
		this.varientDetails = varientDetails;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
}
