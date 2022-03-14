package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CityDto;
import com.omertdemirel.rentacar.business.dtos.ListCityDto;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;

public interface CityService {

	DataResult<CityDto> getById(int cityId) throws BusinessException;

	DataResult<List<ListCityDto>> listAll() throws BusinessException;

	DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;

	DataResult<List<ListCityDto>> getAllSorted(Sort.Direction direction) throws BusinessException;
}
