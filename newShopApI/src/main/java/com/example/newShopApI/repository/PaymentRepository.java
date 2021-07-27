package com.example.newShopApI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newShopApI.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
