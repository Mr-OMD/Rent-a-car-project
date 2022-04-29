package com.omertdemirel.rentacar.business.concretes;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CityService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.CityDto;
import com.omertdemirel.rentacar.business.dtos.ListCityDto;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CityDao;
import com.omertdemirel.rentacar.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCityDto>> listAll(){
		
		Sort sort = Sort.by(Direction.ASC, "cityPlate");
		List<City> cities = this.cityDao.findAll(sort);
		List<ListCityDto> listCityDtos = cities.stream()
			.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(listCityDtos, Messages.CITIESLISTED);
	}

	@Override
	public DataResult<CityDto> getByCityPlate(int cityPlate){
		
		checkCityPlateExists(cityPlate);
		
		City city = this.cityDao.getById(cityPlate);
		CityDto cityDto = this.modelMapperService.forDto().map(city, CityDto.class);
		
		return new SuccessDataResult<CityDto>(cityDto, Messages.CITYGETTEDBYPLATE);
	}

	@Override
	public DataResult<List<ListCityDto>> getAllSorted(Direction direction){
		
		Sort sort = Sort.by(direction, "cityName");
		List<City> cities = this.cityDao.findAll(sort);
		List<ListCityDto> listCityDtos = cities.stream()
			.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(listCityDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize){
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<City> cities = this.cityDao.findAll(pageable).getContent();
		List<ListCityDto> listCityDtos = cities.stream()
			.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(listCityDtos, Messages.DATAINPAGE
			+ Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE + Integer.toString(pageSize));
	}

	private void checkCityPlateExists(int cityPlate){
		if (!this.cityDao.existsById(cityPlate)) {
			
			throw new BusinessException(Messages.CITYNOTFOUND);
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
