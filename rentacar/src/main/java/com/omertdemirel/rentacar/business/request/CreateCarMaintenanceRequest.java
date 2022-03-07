package com.omertdemirel.rentacar.business.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	private String maintenanceDescription;
	private LocalDateTime returnDate;

	private int carId;
}
