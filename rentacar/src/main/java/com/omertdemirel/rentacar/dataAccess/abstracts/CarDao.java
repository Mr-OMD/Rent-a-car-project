package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
	List<Car> getByDailyPriceLessThanEqual(double dailyPrice);
}