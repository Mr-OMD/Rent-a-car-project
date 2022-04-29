package com.omertdemirel.rentacar.business.dtos;

import java.util.List;

import com.omertdemirel.rentacar.entities.concretes.Car;
import com.omertdemirel.rentacar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

	//private int id;
	private String cityName;
	//private List<Car> cars;
	//private List<Rental> rentCityRentals;
	//private List<Rental> returnCityRentals;
}
