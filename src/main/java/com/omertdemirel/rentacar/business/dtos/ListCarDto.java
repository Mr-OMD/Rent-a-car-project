package com.omertdemirel.rentacar.business.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

private int carId;
	
	private String carName;
	
	private double dailyPrice;
	
	private int modelYear;
	
	private String description;
	
	private int kilometerOfCar;
	
	private String brandName;
	
	private String colorName;
	
	private List<ListDamageDto> damages;
}
