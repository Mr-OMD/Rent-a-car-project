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

import com.omertdemirel.rentacar.business.abstracts.ColorService;
import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
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

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
		
		return this.colorService.update(updateColorRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateColorRequest createColorRequest){
		
		return this.colorService.create(createColorRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListColorDto>> listAll(){
		return this.colorService.listAll();
	}

	@GetMapping("/getById")
	public DataResult<ColorDto> getById(@RequestParam int colorId){
		
		return this.colorService.getById(colorId);
	}

	@GetMapping("/getAllSorted")
	DataResult<List<ListColorDto>> getAllSorted(Sort.Direction direction){
		return this.colorService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	DataResult<List<ListColorDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.colorService.getAllPaged(pageNo, pageSize);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int colorId){
		
		return this.colorService.delete(colorId);
	}

}
