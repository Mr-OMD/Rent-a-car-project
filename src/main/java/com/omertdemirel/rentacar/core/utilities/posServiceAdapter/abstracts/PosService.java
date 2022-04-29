package com.omertdemirel.rentacar.core.utilities.posServiceAdapter.abstracts;

import com.omertdemirel.rentacar.entities.concretes.CreditCard;

public interface PosService {
	boolean checkCardIsActive(CreditCard creditCard);
}
