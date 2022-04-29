package com.omertdemirel.rentacar.business.abstracts;

import com.omertdemirel.rentacar.entities.concretes.Customer;

public interface CustomerService {

	Customer setByCustomerId(int customerId);
	
	void checkCustomerExists(int customerId);
	
	void checkEmailExists(String email);
}
