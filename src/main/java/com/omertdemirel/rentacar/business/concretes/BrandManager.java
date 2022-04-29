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

import com.omertdemirel.rentacar.business.abstracts.BrandService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.BrandDto;
import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.BrandDao;
import com.omertdemirel.rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {

		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest){
		
		checkBrandIdExists(updateBrandRequest.getBrandId());
		checkBrandNameExists(updateBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessDataResult<UpdateBrandRequest>(updateBrandRequest,
			Messages.BRANDUPDATED + brand.getBrandName());
	}

	@Override
	@Transactional
	public Result create(CreateBrandRequest createBrandRequest){
		
		checkBrandNameExists(createBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessDataResult<CreateBrandRequest>(createBrandRequest,
			Messages.BRANDADDED + brand.getBrandName());
	}

	@Override
	public DataResult<List<ListBrandDto>> listAll(){
		
		Sort sort = Sort.by(Direction.ASC, "brandId");
		List<Brand> brands = this.brandDao.findAll(sort);
		List<ListBrandDto> listBrandDtos = brands.stream()
			.map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListBrandDto>>(listBrandDtos, Messages.BRANDSLISTED);
	}

	@Override
	public DataResult<BrandDto> getById(int brandId){
		
		checkBrandIdExists(brandId);
		
		Brand brand = this.brandDao.getById(brandId);
		BrandDto brandDto = this.modelMapperService.forDto().map(brand, BrandDto.class);
		
		return new SuccessDataResult<BrandDto>(brandDto, Messages.BRANDGETTEDBYID);
	}

	@Override
	public DataResult<List<ListBrandDto>> getAllSorted(Sort.Direction direction){
		
		Sort sort = Sort.by(direction, "brandName");
		List<Brand> brands = this.brandDao.findAll(sort);
		List<ListBrandDto> listBrandDtos = brands.stream()
			.map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListBrandDto>>(listBrandDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListBrandDto>> getAllPaged(int pageNo, int pageSize){
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Brand> brands = this.brandDao.findAll(pageable).getContent();
		List<ListBrandDto> listBrandDtos = brands.stream()
			.map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListBrandDto>>(listBrandDtos,
			Messages.DATAINPAGE + Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE
			+ Integer.toString(pageSize));
	}

	@Override
	public Result delete(int brandId){
		
		checkBrandIdExists(brandId);
		
		String brandNameBeforeDelete = this.brandDao.findByBrandId(brandId).getBrandName();
		this.brandDao.deleteById(brandId);
		
		return new SuccessResult(Messages.BRANDDELETED + brandNameBeforeDelete);
	}

	private void checkBrandNameExists(String brandName){
		
		if (this.brandDao.existsByBrandName(brandName)) {
			
			throw new BusinessException(Messages.BRANDEXISTS + brandName);
		}
	}

	private void checkBrandIdExists(int brandId){
		
		if (!this.brandDao.existsById(brandId)) {
			
			throw new BusinessException(Messages.BRANDNOTFOUND);
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
