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

@RestController
@RequestMapping("/api/colors")
public class ColorController {

	private final ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}

	@GetMapping("/getall")
	public List<ListColorDto> getAll() {
		return colorService.getAll();
	}

	@PostMapping("/save")
	public ResponseEntity<ColorDto> add(@RequestBody CreateColorRequest createColorRequest) {
		ColorDto result = null;
		try {
			result = colorService.add(createColorRequest);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<ColorDto>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ColorDto get(@RequestParam int id) {
		return colorService.getById(id);
	}
	@DeleteMapping
	public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		colorService.delete(deleteColorRequest);
	}
	@PutMapping
	public void update(@RequestBody UpdateColorRequest updateColorRequest) {
		colorService.update(updateColorRequest);
	}

}
