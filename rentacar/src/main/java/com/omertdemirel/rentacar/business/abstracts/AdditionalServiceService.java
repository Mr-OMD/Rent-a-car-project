package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.AdditionalServiceDto;
import com.omertdemirel.rentacar.business.dtos.ListAdditionalServiceDto;
import com.omertdemirel.rentacar.business.request.CreateAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.DeleteAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface AdditionalServiceService {

	DataResult<List<ListAdditionalServiceDto>> getAll();

	DataResult<List<ListAdditionalServiceDto>> getAllByRentId(int rentId);

	DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException;

	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
}
