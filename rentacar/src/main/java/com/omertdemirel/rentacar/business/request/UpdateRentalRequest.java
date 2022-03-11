package com.omertdemirel.rentacar.business.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	@Min(value = 1, message = "Rent id should be positive integer")
	private int rentId;
	@NotNull
	private LocalDateTime rentDate;
	private LocalDateTime returnDate;
	@Min(value = 1, message = "Car id should be positive integer")
	private int carId;

}