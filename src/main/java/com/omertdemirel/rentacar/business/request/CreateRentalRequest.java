package com.omertdemirel.rentacar.business.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omertdemirel.rentacar.business.dtos.AdditionalServiceIdDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	@NotNull
	private int carId;

	@NotNull
	private int customerId;
	
	@JsonIgnore
	private LocalDate rentalDate;
	
	@NotNull
	private LocalDate returnDate;
	
	@NotNull
	private int currentCityPlate;
	
	@Nullable
	private int returnCityPlate;

	@Nullable
	private List<AdditionalServiceIdDto> additionalServicesIds;
	
	@JsonIgnore
	private double totalPrice;
}
