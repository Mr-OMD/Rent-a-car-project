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

	DataResult<List<ListRentalDto>> getAll();

	DataResult<RentalDto> getById(int id);

	Result add(CreateRentalRequest createRentalRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<List<ListRentalDto>> getAllByCar(int id) throws BusinessException;

	boolean isCarAlreadyRented(int carId);
}
