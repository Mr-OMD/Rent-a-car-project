package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Customer;


@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	boolean existsByUserId(int userId);
	
	boolean existsByEmail(String email);
}
