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

import com.omertdemirel.rentacar.business.abstracts.CreditCardService;
import com.omertdemirel.rentacar.business.dtos.CreditCardDto;
import com.omertdemirel.rentacar.business.dtos.ListCreditCardDto;
import com.omertdemirel.rentacar.business.request.CreateCreditCardRequest;
import com.omertdemirel.rentacar.business.request.UpdateCreditCardRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/creditCards")
public class CreditCardController {

	private CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {

		this.creditCardService = creditCardService;
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
		
		return this.creditCardService.update(updateCreditCardRequest);
	}
	
	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest) {
		
		return this.creditCardService.create(createCreditCardRequest);
	}
	
	@GetMapping("/listAll")
	public DataResult<List<ListCreditCardDto>> listAll(){
		
		return this.creditCardService.listAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CreditCardDto> getById(@RequestParam int creditCardId){
		
		return this.creditCardService.getById(creditCardId);
	}
	
	@GetMapping("/getByCreditCardNumber")
	public DataResult<CreditCardDto> getByCreditCardNumber(String creditCardNumber){
		
		return this.creditCardService.getByCreditCardNumber(creditCardNumber);
	}
	
	@GetMapping("/getAllSorted")
	public DataResult<List<ListCreditCardDto>> getAllSorted(Sort.Direction direction){
		
		return this.creditCardService.getAllSorted(direction);
	}
	
	@GetMapping("/getAllPaged")
	public DataResult<List<ListCreditCardDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.creditCardService.getAllPaged(pageNo, pageSize);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int creditCardId) {
		
		return this.creditCardService.delete(creditCardId);
	}
}
