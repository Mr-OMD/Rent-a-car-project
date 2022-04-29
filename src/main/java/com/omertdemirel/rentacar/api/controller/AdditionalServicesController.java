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

import com.omertdemirel.rentacar.business.abstracts.AdditionalServiceService;
import com.omertdemirel.rentacar.business.dtos.AdditionalServiceDto;
import com.omertdemirel.rentacar.business.dtos.ListAdditionalServiceDto;
import com.omertdemirel.rentacar.business.request.CreateAdditionalServiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalService")
public class AdditionalServicesController {

	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		
		this.additionalServiceService = additionalServiceService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
		
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
		
		return this.additionalServiceService.create(createAdditionalServiceRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListAdditionalServiceDto>> listAll(){
		
		return this.additionalServiceService.listAll();
	}

	@GetMapping("/getById")
	public DataResult<AdditionalServiceDto> getById(@RequestParam int additionalServiceId){
		
		return this.additionalServiceService.getById(additionalServiceId);
	}

	@GetMapping("/getAllSorted")
	DataResult<List<ListAdditionalServiceDto>> getAllSorted(Sort.Direction direction){
		
		return this.additionalServiceService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	DataResult<List<ListAdditionalServiceDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.additionalServiceService.getAllPaged(pageNo, pageSize);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int additionalServiceId){
		
		return this.additionalServiceService.delete(additionalServiceId);
	}
}
