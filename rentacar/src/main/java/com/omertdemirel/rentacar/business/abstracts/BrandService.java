package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.BrandDto;
import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;



public interface BrandService {

	Result update(UpdateBrandRequest updateBrandRequest);

	Result create(CreateBrandRequest createBrandRequest);
	
	Result delete(int brandId);

	DataResult<List<ListBrandDto>> listAll();

	DataResult<BrandDto> getById(int brandId);
	
	DataResult<List<ListBrandDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<List<ListBrandDto>> getAllPaged(int pageNo, int pageSize);

	

}