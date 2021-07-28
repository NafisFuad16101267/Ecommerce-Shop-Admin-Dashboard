package com.example.newShopApI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Payment")
@Table(name = "Payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "payment_type", nullable=false , columnDefinition="TEXT")
	private String paymentType;
	
	@Column(name = "Transaction_ID", nullable=false, columnDefinition="TEXT")
	private String transactionID;
	
	@Column(name = "completed", nullable=false, columnDefinition="tinyint(1) default 1")
	private Boolean completed = true;
	
	@OneToOne
	@JsonIgnore
	private Order order;
	
	public Payment() {
		
	}
	
	public Payment(Long id, String paymentType, String transactionID, Order order) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.transactionID = transactionID;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
