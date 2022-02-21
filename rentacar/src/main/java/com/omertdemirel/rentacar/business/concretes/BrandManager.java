package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.BrandService;

import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.DeleteBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.dataAccess.abstracts.BrandDao;
import com.omertdemirel.rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListBrandDto> getAll() {
		List<Brand> result = brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		if (!doesExist(brand)) {
			brandDao.save(brand);
		}

	}

	@Override
	public ListBrandDto getById(int id) {
		Brand brand = brandDao.getById(id);
		ListBrandDto response = modelMapperService.forDto().map(brand, ListBrandDto.class);
		return response;
	}

	private boolean doesExist(Brand brand) {

		return Objects.nonNull(brandDao.getByBrandName(brand.getBrandName()));
	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		if (brandDao.existsById(updateBrandRequest.getBrandId())) {
			Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
			brandDao.save(brand);
		}
		
	}

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		if (brandDao.existsById(deleteBrandRequest.getBrandId())) {
			brandDao.deleteById(deleteBrandRequest.getBrandId());
		}
		
	}

}
