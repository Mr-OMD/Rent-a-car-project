package com.omertdemirel.rentacar.business.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

	//private int rentId;
	private LocalDate rentalDate;
	private LocalDate returnDate;
	private String currentCity;
	private String returnCity;
	private List<ListAdditionalServiceDto> additionalServices;
	private double totalDailyPrice;
	private int customerId;
	//private int carId;
	private CarDto car;

}