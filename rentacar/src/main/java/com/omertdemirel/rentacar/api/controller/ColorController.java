package com.omertdemirel.rentacar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.ColorService;
import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.DeleteColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

	private final ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}

	@GetMapping("/getall")
	public DataResult<List<ListColorDto>> getAll() {
		return colorService.getAll();
	}

	@GetMapping("/get")
	public DataResult<ColorDto> get(@RequestParam int id) {
		return colorService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return colorService.add(createColorRequest);
	}

	@DeleteMapping
	public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		return colorService.delete(deleteColorRequest);
	}

	@PutMapping
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return colorService.update(updateColorRequest);
	}

}
