package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;



public interface BrandService {

	List<ListBrandDto> getAll();

	void add(CreateBrandRequest createBrandRequest);

	ListBrandDto getById(int id);

}