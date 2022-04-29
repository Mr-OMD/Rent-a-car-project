package com.omertdemirel.rentacar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCreditCardDto {
	
	private int creditCardId;

	private String creditCardNumber;
	
	private String creditCardCvv;
	
	private String creditCardOwnerName;
	
	private LocalDate creditCardExpirationDate;
}
