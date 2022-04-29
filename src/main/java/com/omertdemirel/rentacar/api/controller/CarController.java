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

import com.omertdemirel.rentacar.business.abstracts.CarService;
import com.omertdemirel.rentacar.business.dtos.CarDto;
import com.omertdemirel.rentacar.business.dtos.ListCarDto;
import com.omertdemirel.rentacar.business.request.CreateCarRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest){
		
		return this.carService.update(updateCarRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateCarRequest createCarRequest){
		
		return this.carService.create(createCarRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListCarDto>> listAll(){
		
		return this.carService.listAll();
	}

	@GetMapping("/getById")
	public DataResult<CarDto> getById(@RequestParam int carId){
		
		return this.carService.getById(carId);
	}

	@GetMapping("/getAllSorted")
	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction){
		
		return this.carService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.carService.getAllPaged(pageNo, pageSize);
	}

	@GetMapping("/findByDailyPriceLessThanEqual")
	DataResult<List<ListCarDto>> findByDailyPriceLessThanEqual(double dailyPrice){
		
		return this.carService.findByDailyPriceLessThanEqual(dailyPrice);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int carId){
		
		return this.carService.delete(carId);
	}

}
