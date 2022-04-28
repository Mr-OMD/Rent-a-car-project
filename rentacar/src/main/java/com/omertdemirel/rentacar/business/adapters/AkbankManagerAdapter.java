package com.omertdemirel.rentacar.business.adapters;

import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.outServices.AkbankManager;
import com.omertdemirel.rentacar.core.utilities.posServiceAdapter.abstracts.PosService;
import com.omertdemirel.rentacar.entities.concretes.CreditCard;

@Service
public class AkbankManagerAdapter implements PosService{
	
	@Override
	public boolean checkCardIsActive(CreditCard creditCard) {
		
		AkbankManager akbank = new AkbankManager();
		
		return akbank.makePayment(creditCard.getCreditCardNumber(), creditCard.getCreditCardCvv());
	}

}
