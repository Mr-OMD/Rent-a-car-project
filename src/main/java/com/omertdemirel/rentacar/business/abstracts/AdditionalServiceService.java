package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.AdditionalServiceDto;
import com.omertdemirel.rentacar.business.dtos.ListAdditionalServiceDto;
import com.omertdemirel.rentacar.business.request.CreateAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.entities.concretes.AdditionalService;

public interface AdditionalServiceService {

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

	Result create(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	
	Result delete(int additionalServiceId);

	DataResult<List<ListAdditionalServiceDto>> listAll();

	DataResult<AdditionalServiceDto> getById(int additionalServiceId);

	DataResult<List<ListAdditionalServiceDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListAdditionalServiceDto>> getAllPaged(int pageNo, int pageSize);

	List<AdditionalService> getByRentalId(int rentalId);
}
