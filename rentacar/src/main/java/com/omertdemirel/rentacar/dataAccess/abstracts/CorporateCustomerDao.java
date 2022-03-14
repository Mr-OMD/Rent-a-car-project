package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.CorporateCustomer;


@Repository
public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {

}
