package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.DeleteColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();

	ColorDto add(CreateColorRequest createColorRequest);

	ColorDto getById(int id);
	
	void delete(DeleteColorRequest deleteColorRequest);
	
	void update(UpdateColorRequest updateColorRequest);
}
