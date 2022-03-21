package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.CarAccident;

@Repository
public interface CarAccidentDao extends JpaRepository<CarAccident, Integer> {
	
	List<CarAccident> getAllByCarCarId(int carId);
}
