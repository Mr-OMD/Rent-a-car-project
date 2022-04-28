package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omertdemirel.rentacar.entities.concretes.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

	boolean existsByPaymentId(int paymentId);
	
	Payment getByPaymentId(int paymentId);
	
	List<Payment> getByPaymentRental_RentalId(int rentalId);
	
	Payment getByPaymentInvoice_InvoiceId(int invoiceId);
}
