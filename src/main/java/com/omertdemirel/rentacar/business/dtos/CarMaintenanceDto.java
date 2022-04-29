package com.omertdemirel.rentacar.business.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {

	private String description;
	
	private LocalDate maintenanceDate;
	
	private LocalDate returnDate;
	
	private CarDto car;
}
