package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.AdditionalServiceService;
import com.omertdemirel.rentacar.business.abstracts.RentalService;
import com.omertdemirel.rentacar.business.dtos.AdditionalServiceDto;
import com.omertdemirel.rentacar.business.dtos.ListAdditionalServiceDto;
import com.omertdemirel.rentacar.business.request.CreateAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.DeleteAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.AdditionalServiceDao;
import com.omertdemirel.rentacar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private ModelMapperService modelMapperService;
	private AdditionalServiceDao additionalServiceDao;
	private RentalService rentalService;

	@Autowired
	public AdditionalServiceManager(ModelMapperService modelMapperService, AdditionalServiceDao additionalServiceDao,
			RentalService rentalService) {
		this.modelMapperService = modelMapperService;
		this.additionalServiceDao = additionalServiceDao;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		List<AdditionalService> result = additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = result.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAllByRentId(int rentId) {
		List<AdditionalService> result = additionalServiceDao.getAllByRental(rentId);

		List<ListAdditionalServiceDto> response = result.stream()
				.map(rentalService -> modelMapperService.forDto().map(rentalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);

//		additionalService.setAdditionalId(0);
		additionalServiceDao.save(additionalService);
		return new SuccessResult("additionalService.Added");
	}

	@Override
	public DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException {
		AdditionalService result = additionalServiceDao.getById(additionalServiceId);
		if (Objects.nonNull(result)) {
			AdditionalServiceDto response = modelMapperService.forDto().map(result, AdditionalServiceDto.class);
			return new SuccessDataResult<AdditionalServiceDto>(response);
		}
		throw new BusinessException("The additional service was not found!");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
//		AdditionalService additionalService = additionalServiceDao.getById(updateAdditionalServiceRequest.getAdditionalId());
		isExistById(updateAdditionalServiceRequest.getAdditionalId());
//		additionalService.setDailyPrice(updateAdditionalServiceRequest.getDailyPrice());
//		additionalService.setDescription(updateAdditionalServiceRequest.getDescription());
//		additionalService.setName(updateAdditionalServiceRequest.getName());
		additionalServiceDao.save(additionalService);
		return new SuccessResult("additionalService.Updated");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		isExistById(deleteAdditionalServiceRequest.getAdditionalId());
		additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalId());
		return new SuccessResult();
	}

	private boolean isExistById(int additionalServiceId) throws BusinessException {
		if (additionalServiceDao.existsById(additionalServiceId)) {
			return true;
		}
		throw new BusinessException("The brand with id : " + additionalServiceId + " was not found!");
	}

}