package com.omertdemirel.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
										//rental sorunu???
	private RentalDto paymentRental;
	
	private CreditCardDto paymentCard;
}
