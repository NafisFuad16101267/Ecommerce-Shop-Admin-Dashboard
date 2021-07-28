package com.example.newShopApI.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Product_Category")
@Table(name = "Product_Category")
public class ProductCategory {

	@Id
	@SequenceGenerator(name = "productCategory_sequence", sequenceName = "productCategory_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "productCategory_sequence")
	@Column(name = "id", updatable = false)
	private long id;

	@Column(name = "productcategory_name", nullable = false, columnDefinition = "TEXT")
	private String categoryName;
	
	@Column(name = "productcategory_activity", nullable = false, columnDefinition = "tinyint(1) default 1")
	private Boolean categoryActivity;

	@OneToMany(mappedBy = "productCategory", orphanRemoval = true, cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Product> products;
	
	public ProductCategory() {
		
	}
	
	public ProductCategory(long id, String categoryName, Boolean categoryActivity, List<Product> products) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.products = products;
		this.categoryActivity = categoryActivity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Boolean getCategoryActivity() {
		return categoryActivity;
	}

	public void setCategoryActivity(Boolean categoryActivity) {
		this.categoryActivity = categoryActivity;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
