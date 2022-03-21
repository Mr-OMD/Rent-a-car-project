package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omertdemirel.rentacar.entities.concretes.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

	List<Payment> getByCardOwnerName(String cardOwnerName);

	List<Payment> getByCardNumber(String cardNumber);
}
