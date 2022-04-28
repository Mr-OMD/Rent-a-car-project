package com.omertdemirel.rentacar.business.adapters;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.outServices.ZiraatBankasiManager;
import com.omertdemirel.rentacar.core.utilities.posServiceAdapter.abstracts.PosService;
import com.omertdemirel.rentacar.entities.concretes.CreditCard;

@Service
@Primary
public class ZiraatBankasiManagerAdapter implements PosService{

	@Override
	public boolean checkCardIsActive(CreditCard creditCard) {
		
		ZiraatBankasiManager ziraatBankasi = new ZiraatBankasiManager();
		
		return ziraatBankasi.makePayment(creditCard.getCreditCardNumber(), creditCard.getCreditCardCvv(),
				creditCard.getCreditCardExpirationDate(), creditCard.getCreditCardOwnerName());
	}

}
