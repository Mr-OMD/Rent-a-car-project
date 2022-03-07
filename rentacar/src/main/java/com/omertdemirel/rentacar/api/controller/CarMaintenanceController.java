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

import com.omertdemirel.rentacar.business.abstracts.CarMaintenanceService;
import com.omertdemirel.rentacar.business.dtos.CarMaintenanceDto;
import com.omertdemirel.rentacar.business.dtos.ListCarMaintenanceDto;
import com.omertdemirel.rentacar.business.request.CreateCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.omertdemirel.rentacar.business.request.UpdateCarMaintenanceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/maintenances")
public class CarMaintenanceController {

	private final CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		return carMaintenanceService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<CarMaintenanceDto> get(@RequestParam int id) {
		return carMaintenanceService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@PutMapping("/update")
	public Result delete(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@GetMapping("/getAllByCar/{id}")
	public DataResult<List<ListCarMaintenanceDto>> getAllByCar(@RequestParam int id) {
		return carMaintenanceService.getAllByCar(id);
	}

}
