package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.CarMaintenanceDto;
import com.omertdemirel.rentacar.business.dtos.ListCarMaintenanceDto;
import com.omertdemirel.rentacar.business.request.CreateCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarMaintenanceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface CarMaintenanceService {

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

	Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	
	Result delete(int carMaintenanceId);

	DataResult<List<ListCarMaintenanceDto>> listAll();

	DataResult<CarMaintenanceDto> getById(int carMaintenanceId);

	DataResult<List<ListCarMaintenanceDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId);
	
	void checkCarAlreadyMaintenanced(int carId);

}
