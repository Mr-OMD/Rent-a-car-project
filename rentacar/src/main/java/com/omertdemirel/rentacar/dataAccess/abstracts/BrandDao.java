package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
	Brand getByBrandName(String name);
	boolean existsByBrandName(String name);
	Brand findByBrandId(int id);
}
