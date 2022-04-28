package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.IndividualCustomer;


@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
	
	boolean existsByNationalIdentity(String nationalIdentity);
	
	IndividualCustomer getByNationalIdentity(String nationalIdentity);
	
	IndividualCustomer getByFirstname(String firstname);
	
	IndividualCustomer getByLastname(String lastname);
	
	boolean existsByFirstname(String firstname);
	
	boolean existsByLastname(String lastname);
}
