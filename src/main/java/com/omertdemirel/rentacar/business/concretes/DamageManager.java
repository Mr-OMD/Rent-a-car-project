package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omertdemirel.rentacar.business.abstracts.CarService;
import com.omertdemirel.rentacar.business.abstracts.DamageService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.DamageDto;
import com.omertdemirel.rentacar.business.dtos.ListDamageDto;
import com.omertdemirel.rentacar.business.request.CreateDamageRequest;
import com.omertdemirel.rentacar.business.request.UpdateDamageRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.DamageDao;
import com.omertdemirel.rentacar.entities.concretes.Damage;

@Service
public class DamageManager implements DamageService {
	
	private DamageDao damageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	@Autowired
	public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService, CarService carService) {
		
		this.damageDao = damageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result update(UpdateDamageRequest updateDamageRequest) {
		
		checkCarDamageIdExists(updateDamageRequest.getDamageId());
		
		Damage damage = this.modelMapperService.forRequest().map(updateDamageRequest, Damage.class);
		this.damageDao.save(damage);
		
		return new SuccessDataResult<UpdateDamageRequest>(updateDamageRequest,
			Messages.DAMAGEUPDATED + damage.getDamageDescription());
	}

	@Override
	@Transactional
	public Result create(CreateDamageRequest createDamageRequest) {
		
		checkCarExists(createDamageRequest.getCarId());
		
		Damage damage = this.modelMapperService.forRequest().map(createDamageRequest, Damage.class);
		this.damageDao.save(damage);
		
		return new SuccessDataResult<CreateDamageRequest>(createDamageRequest,
			Messages.DAMAGEADDED + damage.getDamageDescription());
	}

	@Override
	public DataResult<List<ListDamageDto>> listAll() {
		
		Sort sort = Sort.by(Direction.ASC, "damageId");
		List<Damage> damages = this.damageDao.findAll(sort);
		List<ListDamageDto> listDamageDtos = damages.stream()
			.map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListDamageDto>>(listDamageDtos, Messages.DAMAGESLISTED);
	}

	@Override
	public DataResult<DamageDto> getById(int damageId) {
		
		checkCarDamageIdExists(damageId);
		
		Damage damage = this.damageDao.getById(damageId);
		DamageDto damageDto = this.modelMapperService.forDto().map(damage, DamageDto.class);
		
		return new SuccessDataResult<DamageDto>(damageDto, Messages.DAMAGEGETTEDBYID);
	}

	@Override
	public DataResult<List<ListDamageDto>> getAllSorted(Direction direction) {
		
		Sort sort = Sort.by(direction, "damageId");
		List<Damage> damages = this.damageDao.findAll(sort);
		List<ListDamageDto> listDamageDtos = damages.stream()
			.map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListDamageDto>>(listDamageDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize) {
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Damage> damages = this.damageDao.findAll(pageable).getContent();
		List<ListDamageDto> listDamageDtos = damages.stream()
			.map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListDamageDto>>(listDamageDtos, Messages.DATAINPAGE
			+ Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE + Integer.toString(pageSize));
	}

	@Override
	public Result delete(int damageId) {
		
		checkCarDamageIdExists(damageId);
		
		this.damageDao.deleteById(damageId);
		
		return new SuccessResult(Messages.DAMAGEDELETED + damageId);
	}
	
	private void checkCarDamageIdExists(int damageId) {
		
		if(!this.damageDao.existsByDamageId(damageId)) {
			
			throw new BusinessException(Messages.DAMAGENOTFOUND);
		}
	}
	
	private void checkCarExists(int carId) {
		
		if(!this.carService.getById(carId).isSuccess()) {
			
			throw new BusinessException(Messages.CARNOTFOUND);
		}
	}
	
	private void checkPageNoAndPageSize(int pageNo, int pageSize) {
		
		if(pageNo <= 0) {
			
			throw new BusinessException(Messages.PAGENOCANNOTLESSTHANZERO);
		}else if(pageSize <= 0) {
			
			throw new BusinessException(Messages.PAGESIZECANNOTLESSTHANZERO);
		}
	}
}
