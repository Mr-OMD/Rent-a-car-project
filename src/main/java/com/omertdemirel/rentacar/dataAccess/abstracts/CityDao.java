package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.City;



@Repository
public interface CityDao extends JpaRepository<City, Integer> {

	City getByCityName(int cityName);
	boolean existsByCityName(String cityName);
	City findByCityPlate(int cityPlate);

}