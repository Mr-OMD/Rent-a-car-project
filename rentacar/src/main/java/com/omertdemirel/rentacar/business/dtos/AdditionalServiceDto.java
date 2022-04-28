package com.omertdemirel.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {

	private int additionalId;
	private String additional_service_name;
	private int dailyPrice;
	private int rentalId;
}