package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.DeleteBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;



public interface BrandService {

	List<ListBrandDto> getAll();

	void add(CreateBrandRequest createBrandRequest);

	ListBrandDto getById(int id);
	
	void update(UpdateBrandRequest updateBrandRequest);

	void delete(DeleteBrandRequest deleteBrandRequest);

}