package com.example.newShopApI.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Product")
@Table(name = "Product")
public class Product {
	@Id
	@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "product_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "product_name", nullable = false, columnDefinition = "TEXT")
	private String productName;
	
	@Lob
	@Column(name = "product_description", nullable = false, columnDefinition = "TEXT")
	private String productDescription;

	@Lob
    @Column(name="product_icon", nullable=false, columnDefinition="MEDIUMBLOB")
	private String productIcon;
	
	@Column(name = "product_stock", nullable = false)
	private Long stock;

	@ManyToOne
	private ProductCategory productCategory;

	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<ProductVarient> productVarient;
	
	public Product() {
		
	}

	public Product(String productName, String productDescription, String productIcon, Long stock,
			ProductCategory productCategory, List<ProductVarient> productVarient) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productIcon = productIcon;
		this.stock = stock;
		this.productCategory = productCategory;
		this.productVarient = productVarient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductIcon() {
		return productIcon;
	}

	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}
	
	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductVarient> getProductVarient() {
		return productVarient;
	}

	public void setProductVarient(List<ProductVarient> productVarient) {
		this.productVarient = productVarient;
	}

}
