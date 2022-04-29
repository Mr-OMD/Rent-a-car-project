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

import com.omertdemirel.rentacar.business.abstracts.CorporateCustomerService;
import com.omertdemirel.rentacar.business.dtos.CorporateCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListCorporateCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.UpdateCorporateCustomerRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomerController {

	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
		
		this.corporateCustomerService = corporateCustomerService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest){
		
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest){
		
		return this.corporateCustomerService.create(createCorporateCustomerRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListCorporateCustomerDto>> listAll(){
		
		return this.corporateCustomerService.listAll();
	}
	
	@GetMapping("getByCorporateCustomerId")
	DataResult<CorporateCustomerDto> getByCorporateCustomerId(int corporateCustomerId){
		
		return this.corporateCustomerService.getByCorporateCustomerId(corporateCustomerId);
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListCorporateCustomerDto>> getAllSorted(Sort.Direction direction){
		return this.corporateCustomerService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.corporateCustomerService.getAllPaged(pageNo, pageSize);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int corporateCustomerId){
		
		return this.corporateCustomerService.delete(corporateCustomerId);
	}
}
