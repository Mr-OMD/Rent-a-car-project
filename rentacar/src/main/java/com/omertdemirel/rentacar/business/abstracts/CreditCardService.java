package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CreditCardDto;
import com.omertdemirel.rentacar.business.dtos.ListCreditCardDto;
import com.omertdemirel.rentacar.business.request.CreateCreditCardRequest;
import com.omertdemirel.rentacar.business.request.UpdateCreditCardRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface CreditCardService {

	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	
	Result create(CreateCreditCardRequest createCreditCardRequest);
	
	Result delete(int creditCardId);
	
	DataResult<List<ListCreditCardDto>> listAll();
	
	DataResult<CreditCardDto> getById(int creditCardId);
	
	DataResult<CreditCardDto> getByCreditCardNumber(String creditCardNumber);
	
	DataResult<List<ListCreditCardDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<List<ListCreditCardDto>> getAllPaged(int pageNo, int pageSize);
	
	
}
