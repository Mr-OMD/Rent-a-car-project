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
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.ErrorResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.ColorDao;
import com.omertdemirel.rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private final ColorDao colorDao;
	private final ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		List<Color> result = colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		if (!colorDao.existsByColorName(createColorRequest.getColorName())) {
			Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
			colorDao.save(color);
			return new SuccessResult();
		}
		return new ErrorResult("The color already exist!");
	}

	@Override
	public DataResult<ColorDto> getById(int id) {
		Color color = colorDao.getById(id);
		ColorDto response = modelMapperService.forDto().map(color, ColorDto.class);
		return new SuccessDataResult<ColorDto>(response);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		if (colorDao.existsById(deleteColorRequest.getColorId())) {
			colorDao.deleteById(deleteColorRequest.getColorId());
			return new SuccessResult();
		}
		return new ErrorResult("The brand was not found!");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		if (colorDao.existsById(updateColorRequest.getColorId())) {
			Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
			colorDao.save(color);
			return new SuccessResult();
		}
		return new ErrorResult("The brand was not found!");

	}

}
