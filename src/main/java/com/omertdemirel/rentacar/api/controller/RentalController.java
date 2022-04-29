package com.omertdemirel.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.RentalService;
import com.omertdemirel.rentacar.business.dtos.ListRentalDto;
import com.omertdemirel.rentacar.business.dtos.RentalDto;
import com.omertdemirel.rentacar.business.request.CreateRentalRequest;
import com.omertdemirel.rentacar.business.request.UpdateRentalRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	private final RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
		
		return this.rentalService.update(updateRentalRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateRentalRequest createRentalRequest){
		
		return this.rentalService.create(createRentalRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListRentalDto>> listAll(){
		
		return this.rentalService.listAll();
	}

	@GetMapping("/getById")
	public DataResult<RentalDto> getById(@RequestParam int rentalId){
		
		return this.rentalService.getById(rentalId);
	}

	@GetMapping("/getAllPaged")
	DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.rentalService.getAllPaged(pageNo, pageSize);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int rentalId){
		
		return this.rentalService.delete(rentalId);
	}

}
