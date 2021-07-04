package com.example.shopapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String paymentType;
	private Long transactionId;
	private boolean completed;
	@OneToOne
	private Order order;

	public Payment() {

	}

	public Long getPaymentId() {
		return id;
	}

	public void setPaymentId(Long id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
