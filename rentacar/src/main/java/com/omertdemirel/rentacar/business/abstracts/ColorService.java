package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface ColorService {

	Result update(UpdateColorRequest updateColorRequest);

	Result create(CreateColorRequest createColorRequest);
	
	Result delete(int colorId);

	DataResult<List<ListColorDto>> listAll();

	DataResult<ColorDto> getById(int colorId);

	DataResult<List<ListColorDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListColorDto>> getAllPaged(int pageNo, int pageSize);

	
}
