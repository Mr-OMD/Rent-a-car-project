package com.omertdemirel.rentacar.business.abstracts;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CarDto;
import com.omertdemirel.rentacar.business.dtos.ListCarDto;
import com.omertdemirel.rentacar.business.request.CreateCarRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.entities.concretes.Car;

public interface CarService {
	Result update(UpdateCarRequest updateCarRequest);

	Result create(CreateCarRequest createCarRequest);

	DataResult<List<ListCarDto>> listAll();

	DataResult<CarDto> getById(int carId);

	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

	DataResult<List<ListCarDto>> findByDailyPriceLessThanEqual(double dailyPrice);

	Result delete(int carId);
	
	void updateKilometer(Car car);
}