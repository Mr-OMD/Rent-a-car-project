package com.omertdemirel.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
									//Rental Sorunu???
	private int paymentId;
	
	private RentalDto paymentRental;
	
	private CreditCardDto paymentCard;
}
