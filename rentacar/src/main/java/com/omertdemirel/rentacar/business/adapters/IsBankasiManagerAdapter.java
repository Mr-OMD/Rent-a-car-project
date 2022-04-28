package com.omertdemirel.rentacar.business.adapters;

import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.outServices.IsBankasiManager;
import com.omertdemirel.rentacar.core.utilities.posServiceAdapter.abstracts.PosService;
import com.omertdemirel.rentacar.entities.concretes.CreditCard;

@Service
public class IsBankasiManagerAdapter implements PosService{

	@Override
	public boolean checkCardIsActive(CreditCard creditCard) {
		
		IsBankasiManager isBankasi = new IsBankasiManager();
		
		return isBankasi.makePayment(creditCard.getCreditCardNumber(), creditCard.getCreditCardCvv(),
				creditCard.getCreditCardExpirationDate());
	}

}
