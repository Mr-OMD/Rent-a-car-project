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

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;

	Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;

	DataResult<IndividualCustomerDto> getById(int id) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException;

}
