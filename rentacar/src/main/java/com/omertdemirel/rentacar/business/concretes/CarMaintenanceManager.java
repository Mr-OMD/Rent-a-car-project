package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CarMaintenanceService;
import com.omertdemirel.rentacar.business.dtos.CarMaintenanceDto;
import com.omertdemirel.rentacar.business.dtos.ListCarMaintenanceDto;
import com.omertdemirel.rentacar.business.request.CreateCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarMaintenanceRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.ErrorResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CarDao;
import com.omertdemirel.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.omertdemirel.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private final CarMaintenanceDao carMaintenanceDao;
	private final ModelMapperService modelMapperService;
	private final CarDao carDao;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			CarDao carDao) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		List<CarMaintenance> result = carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public DataResult<CarMaintenanceDto> getById(int id) {
		CarMaintenance carMaintenance = carMaintenanceDao.getById(id);
		CarMaintenanceDto response = modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

		return new SuccessDataResult<CarMaintenanceDto>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {

		CarMaintenance maintenance = new CarMaintenance();
		maintenance.setCar(carDao.getById(createCarMaintenanceRequest.getCarId()));
		maintenance.setMaintenanceDescription(createCarMaintenanceRequest.getMaintenanceDescription());
//		maintenance.setReturnDate(createCarMaintenanceRequest.getReturnDate());
		carMaintenanceDao.save(maintenance);
		//TODO 					MANUEL EKLEME(Oguzhana tekrar sor)
//		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
//				CarMaintenance.class);
//		System.err.println(carMaintenance.toString());
//		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		if (carMaintenanceDao.existsById(updateCarMaintenanceRequest.getMaintenanceId())) {
			CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest,
					CarMaintenance.class);
			carMaintenanceDao.save(carMaintenance);
			return new SuccessResult();
		}
		return new ErrorResult("The maintenance was not found!");
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		if (carMaintenanceDao.existsById(deleteCarMaintenanceRequest.getMaintenanceId())) {
			carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getMaintenanceId());
			return new SuccessResult();
		}
		return new ErrorResult("The maintenance was not found!");
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAllByCar(int id) {
		List<CarMaintenance> result = carMaintenanceDao.getAllByCarCarId(id);
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

}
