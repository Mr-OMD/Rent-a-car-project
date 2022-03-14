package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CorporateCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListCorporateCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.DeleteCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.UpdateCorporateCustomerRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService {

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;

	Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;

	DataResult<CorporateCustomerDto> getById(int id) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException;
}
