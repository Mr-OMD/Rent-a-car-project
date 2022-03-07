package com.omertdemirel.rentacar.business.dtos;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {

	private int rentId;
	private LocalDateTime rentDate;
	private LocalDateTime returnDate;
	private int carId;
}
