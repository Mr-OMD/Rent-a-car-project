package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.ColorService;
import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.DeleteColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.dataAccess.abstracts.ColorDao;
import com.omertdemirel.rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListColorDto> getAll() {
		List<Color> result = colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public ColorDto add(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		if (!doesExist(color)) {
			colorDao.save(color);
		}
		return modelMapperService.forDto().map(color, ColorDto.class);

	}

	@Override
	public ColorDto getById(int id) {
		Color color = colorDao.getById(id);
		ColorDto response = modelMapperService.forDto().map(color, ColorDto.class);
		return response;
	}

	private boolean doesExist(Color color) {
		return Objects.nonNull(colorDao.getByColorName(color.getColorName()));
	}

	@Override
	public void delete(DeleteColorRequest deleteColorRequest) {
		if (colorDao.existsById(deleteColorRequest.getColorId())) {
			colorDao.deleteById(deleteColorRequest.getColorId());
		}

	}

	@Override
	public void update(UpdateColorRequest updateColorRequest) {
		if (colorDao.existsById(updateColorRequest.getColorId())) {
			Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
			colorDao.save(color);
		}

	}

}
