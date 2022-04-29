package com.omertdemirel.rentacar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCorporateCustomerDto {

	private int corporateCustomerId;
	private String corporateName;
	private String taxNo;
	private String email;
	private String password;
	private LocalDate registeredDate;
}
