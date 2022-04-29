package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ListRentalDto;
import com.omertdemirel.rentacar.business.dtos.RentalDto;
import com.omertdemirel.rentacar.business.request.CreateRentalRequest;
import com.omertdemirel.rentacar.business.request.DeleteRentalRequest;
import com.omertdemirel.rentacar.business.request.UpdateRentalRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface RentalService {

	Result update(UpdateRentalRequest updateRentalRequest);

	Result create(CreateRentalRequest createRentalRequest);
	
	Result delete(int rentalId);

	DataResult<List<ListRentalDto>> listAll();

	DataResult<RentalDto> getById(int rentalId);

	DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize);

	void checkCarAlreadyRented(int carId);
}
