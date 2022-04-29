package com.omertdemirel.rentacar.business.outServices;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class ZiraatBankasiManager{

	public boolean makePayment(String cardNumber, String cardCvv, LocalDate cardExpirationDate,
			String carOwnerName) {
		
		int lastDigits = Integer.valueOf(cardNumber.substring(13));
		int cvv = Integer.valueOf(cardCvv);
		
		if(lastDigits+cvv == 999) {
			
			return true;
		}
		
		return false;
	}

}
