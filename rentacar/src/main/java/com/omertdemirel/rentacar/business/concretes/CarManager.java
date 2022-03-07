package com.omertdemirel.rentacar.business.concretes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CarService;
import com.omertdemirel.rentacar.business.dtos.CarDto;
import com.omertdemirel.rentacar.business.dtos.ListCarDto;
import com.omertdemirel.rentacar.business.request.CreateCarRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.ErrorResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CarDao;
import com.omertdemirel.rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private final CarDao carDao;
	private final ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		List<Car> result = carDao.findAll();
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<CarDto> getById(int id) {
		Car car = carDao.getById(id);
		CarDto response = modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<CarDto>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		carDao.save(car);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		if (carDao.existsById(deleteCarRequest.getCarId())) {
			carDao.deleteById(deleteCarRequest.getCarId());
			return new SuccessResult();
		}
		return new ErrorResult("The car was not found!");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		if (carDao.existsById(updateCarRequest.getCarId())) {
			Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
			carDao.save(car);
			return new SuccessResult();
		}
		return new ErrorResult("The car was not found!");
	}

	@Override
	public DataResult<List<ListCarDto>> getByDailyPriceLessThan(BigDecimal maxDailyPrice) {
		var result = carDao.getByDailyPriceLessThanEqual(maxDailyPrice);

		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = carDao.findAll(pageable).getContent();

		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Direction direction) {

		Sort sort = Sort.by(direction, "dailyPrice");

		List<Car> result = carDao.findAll(sort);
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response);
	}

}
