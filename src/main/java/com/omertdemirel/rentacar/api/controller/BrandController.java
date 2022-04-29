package com.omertdemirel.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.BrandService;
import com.omertdemirel.rentacar.business.dtos.BrandDto;
import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
		
		return this.brandService.update(updateBrandRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateBrandRequest createBrandRequest){
		
		return this.brandService.create(createBrandRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListBrandDto>> listAll(){
		
		return this.brandService.listAll();
	}

	@GetMapping("/getById")
	public DataResult<BrandDto> getById(@RequestParam int brandId){
		
		return this.brandService.getById(brandId);
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListBrandDto>> getAllSorted(Sort.Direction direction){
		
		return this.brandService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListBrandDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.brandService.getAllPaged(pageNo, pageSize);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int brandId){
		
		return this.brandService.delete(brandId);
	}
}
