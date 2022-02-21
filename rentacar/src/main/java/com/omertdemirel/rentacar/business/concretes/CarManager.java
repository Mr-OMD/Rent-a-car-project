package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CarService;
import com.omertdemirel.rentacar.business.dtos.CarDto;
import com.omertdemirel.rentacar.business.dtos.ListCarDto;
import com.omertdemirel.rentacar.business.request.CreateCarRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.dataAccess.abstracts.CarDao;
import com.omertdemirel.rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private final CarDao carDao;
	private final ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListCarDto> getAll() {
		List<Car> result = carDao.findAll();
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CarDto getById(int id) {
		Car car = carDao.getById(id);
		CarDto response = modelMapperService.forDto().map(car, CarDto.class);
		return response;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		carDao.save(car);
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) {
		if (carDao.existsById(deleteCarRequest.getCarId())) {
			carDao.deleteById(deleteCarRequest.getCarId());
		}
	}
	
	@Override
	public void delete(int carId) {
		delete(DeleteCarRequest.builder().carId(carId).build());
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		if (carDao.existsById(updateCarRequest.getCarId())) {
			Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
			carDao.save(car);
		}
	}

}
