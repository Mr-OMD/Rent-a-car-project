package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.RentalService;
import com.omertdemirel.rentacar.business.dtos.ListRentalDto;
import com.omertdemirel.rentacar.business.dtos.RentalDto;
import com.omertdemirel.rentacar.business.request.CreateRentalRequest;
import com.omertdemirel.rentacar.business.request.DeleteRentalRequest;
import com.omertdemirel.rentacar.business.request.UpdateRentalRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.ErrorResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.omertdemirel.rentacar.dataAccess.abstracts.RentalDao;
import com.omertdemirel.rentacar.entities.concretes.CarMaintenance;
import com.omertdemirel.rentacar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private final RentalDao rentalDao;
	private final ModelMapperService modelMapperService;
	private final CarMaintenanceDao carMaintenanceDao;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			CarMaintenanceDao carMaintenanceDao) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceDao = carMaintenanceDao;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		List<Rental> result = rentalDao.findAll();
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<RentalDto> getById(int id) {
		Rental rental = rentalDao.getById(id);
		RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);
		return new SuccessDataResult<RentalDto>(response);
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		if (isCarInMaintenance(rental)) {
			return new ErrorResult("The car is under maintenance");
		}
		rentalDao.save(rental);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		if (rentalDao.existsById(updateRentalRequest.getRentId())) {
			Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
			rentalDao.save(rental);
			return new SuccessResult();
		}
		return new ErrorResult("The rental was not found!");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		if (rentalDao.existsById(deleteRentalRequest.getRentId())) {
			rentalDao.deleteById(deleteRentalRequest.getRentId());
			return new SuccessResult();
		}
		return new ErrorResult("The rental was not found!");
	}

	private boolean isCarInMaintenance(Rental rental) {
		List<CarMaintenance> result = carMaintenanceDao.getAllByCarCarId(rental.getCar().getCarId());
		if (Objects.nonNull(result)) {
			for (CarMaintenance carMaintenance : result) {
				if (Objects.isNull(carMaintenance.getReturnDate())
						|| carMaintenance.getReturnDate().isAfter(rental.getRentDate())) {
					return false;
				}
			}
		}
		return true;
	}

}
