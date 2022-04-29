package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.IndividualCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListIndividualCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateIndividualCustomerRequest;
import com.omertdemirel.rentacar.business.request.DeleteIndividualCustomerRequest;
import com.omertdemirel.rentacar.business.request.UpdateIndividualCustomerRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	
	Result delete(int individualCustomerId);

	DataResult<List<ListIndividualCustomerDto>> listAll();

	DataResult<IndividualCustomerDto> getByIndividualCustomerId(int individualCustomerId);

	DataResult<List<ListIndividualCustomerDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListIndividualCustomerDto>> getAllPaged(int pageNo, int pageSize);

}
