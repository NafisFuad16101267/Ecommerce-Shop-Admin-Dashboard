package com.example.shopapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String userEmail;
	private boolean userType; // (True = admin and False = non-admin)
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	//private List<Products> cart; 

	public User() {

	}

	public Long getUserId() {
		return id;
	}

	public void setUserId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isUserType() {
		return userType;
	}

	public void setUserType(boolean userType) {
		this.userType = userType;
	}

}
