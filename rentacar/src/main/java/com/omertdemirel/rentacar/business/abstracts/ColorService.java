package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();

	void add(CreateColorRequest createColorRequest);

	ListColorDto getById(int id);
}
