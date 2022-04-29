package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CorporateCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListCorporateCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.UpdateCorporateCustomerRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService {

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result delete(int corporateCustomerId);

	DataResult<List<ListCorporateCustomerDto>> listAll();

	DataResult<CorporateCustomerDto> getByCorporateCustomerId(int corporateCustomerId);

	DataResult<List<ListCorporateCustomerDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize);

}
