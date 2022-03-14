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

	private int rentId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private String rentCityName;
	private String returnCityName;
	private List<ListAdditionalServiceDto> additionalServices;
	private BigDecimal rentalDailyPrice;
	private int customerId;
	private int carId;

}