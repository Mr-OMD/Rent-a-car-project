package com.omertdemirel.rentacar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CustomerService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.dataAccess.abstracts.CustomerDao;
import com.omertdemirel.rentacar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{

	private CustomerDao customerDao;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao) {
		
		this.customerDao = customerDao;
	}
	
	@Override
	public Customer setByCustomerId(int customerId) {
		
		Customer customer = this.customerDao.getById(customerId);
		
		if (customer == null) {
			return null;
		}
		
		return customer;
	}
	
	@Override
	public void checkCustomerExists(int customerId) {
		
		if(!this.customerDao.existsById(customerId)) {
			
			throw new BusinessException(Messages.CUSTOMERNOTFOUND);
		}
	}
	
	@Override
	public void checkEmailExists(String email) {
		
		if(this.customerDao.existsByEmail(email)) {
			
			throw new BusinessException(Messages.EMAILEXISTS);
		}
	}
}
