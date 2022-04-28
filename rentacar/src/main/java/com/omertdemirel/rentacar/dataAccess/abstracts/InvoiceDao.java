package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Invoice;
@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
	Invoice getByInvoiceId(int invoiceId);
	
	boolean existsByInvoiceId(int invoiceId);
	
	Invoice getByInvoiceNumber(String invoiceNumber);
	
	boolean existsByInvoiceNumber(String invoiceNumber);
	
	Invoice deleteByInvoiceId(int invoiceId);
	
	boolean existsByInvoiceCustomer_UserId(int customerId);
	
	List<Invoice> findByInvoiceCustomer_UserId(int customerId);
		
	List<Invoice> findByCreateDateBetween(LocalDate startDate, LocalDate finishDate);
	
	
}
