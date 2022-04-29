package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.CityDto;
import com.omertdemirel.rentacar.business.dtos.ListCityDto;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;

public interface CityService {

	DataResult<CityDto> getByCityPlate(int cityPlate);
	
	DataResult<List<ListCityDto>> listAll();

	DataResult<List<ListCityDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize);
}
