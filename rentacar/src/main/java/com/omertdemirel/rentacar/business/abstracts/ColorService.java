package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.DeleteColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface ColorService {

	DataResult<List<ListColorDto>> getAll();

	DataResult<ColorDto> getById(int id);

	Result add(CreateColorRequest createColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);
}
