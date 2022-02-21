package com.omertdemirel.rentacar.business.abstracts;
import java.util.List;

import com.omertdemirel.rentacar.business.dtos.CarDto;
import com.omertdemirel.rentacar.business.dtos.ListCarDto;
import com.omertdemirel.rentacar.business.request.CreateCarRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarRequest;

public interface CarService {
	List<ListCarDto> getAll();

	CarDto getById(int id);

	void add(CreateCarRequest createCarRequest);

	void delete(DeleteCarRequest deleteCarRequest);

	void update(UpdateCarRequest updateCarRequest);

	void delete(int carId);
}
