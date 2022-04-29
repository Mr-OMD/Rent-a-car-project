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

import com.omertdemirel.rentacar.business.abstracts.DamageService;
import com.omertdemirel.rentacar.business.dtos.DamageDto;
import com.omertdemirel.rentacar.business.dtos.ListDamageDto;
import com.omertdemirel.rentacar.business.request.CreateDamageRequest;
import com.omertdemirel.rentacar.business.request.UpdateDamageRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/damages")
public class DamageController {

	private DamageService damageService;

	@Autowired
	public DamageController(DamageService damageService) {
		
		this.damageService = damageService;
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateDamageRequest updateDamageRequest) {
		
		return this.damageService.update(updateDamageRequest);
	}
	
	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateDamageRequest createDamageRequest) {
		
		return this.damageService.create(createDamageRequest);
	}
	
	@GetMapping("/listAll")
	public DataResult<List<ListDamageDto>> listAll(){
		
		return this.damageService.listAll();
	}
	
	@GetMapping("/getById")
	public DataResult<DamageDto> getById(@RequestParam int damageId){
		
		return this.damageService.getById(damageId);
	}
	
	@GetMapping("/getAllSorted")
	public DataResult<List<ListDamageDto>> getAllSorted(Sort.Direction direction){
		
		return this.damageService.getAllSorted(direction);
	}
	
	@GetMapping("/getAllPaged")
	public DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.damageService.getAllPaged(pageNo, pageSize);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int damageId) {
		
		return this.damageService.delete(damageId);
	}
}
