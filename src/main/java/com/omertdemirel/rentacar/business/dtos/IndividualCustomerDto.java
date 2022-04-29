package com.omertdemirel.rentacar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

	//private int id;
	private String nationalId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private LocalDate registeredDate;

}
