package com.omertdemirel.rentacar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.BrandService;
import com.omertdemirel.rentacar.business.dtos.ListBrandDto;
import com.omertdemirel.rentacar.business.request.CreateBrandRequest;
import com.omertdemirel.rentacar.business.request.DeleteBrandRequest;
import com.omertdemirel.rentacar.business.request.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public List<ListBrandDto> getAll() {
		return brandService.getAll();
	}

	@PostMapping("/save")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}

	@GetMapping("/get")
	public ListBrandDto get(@RequestParam int id) {
		return brandService.getById(id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		brandService.delete(deleteBrandRequest);
	}
	@PutMapping("/update")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		brandService.update(updateBrandRequest);
	}

}
