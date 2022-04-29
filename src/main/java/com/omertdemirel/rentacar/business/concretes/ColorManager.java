package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omertdemirel.rentacar.business.abstracts.ColorService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.ColorDto;
import com.omertdemirel.rentacar.business.dtos.ListColorDto;
import com.omertdemirel.rentacar.business.request.CreateColorRequest;
import com.omertdemirel.rentacar.business.request.UpdateColorRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
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
	public Result update(UpdateColorRequest updateColorRequest){
		
		checkColorIdExists(updateColorRequest.getColorId());
		checkColorNameExists(updateColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		
		return new SuccessDataResult<UpdateColorRequest>(updateColorRequest,
			Messages.COLORUPDATED + color.getColorName());
	}

	@Override
	@Transactional
	public Result create(CreateColorRequest createColorRequest){
		
		checkColorNameExists(createColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		
		return new SuccessDataResult<CreateColorRequest>(createColorRequest,
			Messages.COLORADDED + color.getColorName());
	}

	@Override
	public DataResult<List<ListColorDto>> listAll(){
		
		Sort sort = Sort.by(Direction.ASC, "colorId");
		List<Color> colors = this.colorDao.findAll(sort);
		List<ListColorDto> listColorDtos = colors.stream()
			.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListColorDto>>(listColorDtos, Messages.COLORSLISTED);
	}

	@Override
	public DataResult<ColorDto> getById(int colorId){
		
		checkColorIdExists(colorId);
		
		Color color = this.colorDao.getById(colorId);
		ColorDto colorDto = this.modelMapperService.forDto().map(color, ColorDto.class);
		
		return new SuccessDataResult<ColorDto>(colorDto, Messages.COLORGETTEDBYID);
	}

	@Override
	public DataResult<List<ListColorDto>> getAllSorted(Sort.Direction direction){
		
		Sort sort = Sort.by(direction, "colorName");
		List<Color> colors = this.colorDao.findAll(sort);
		List<ListColorDto> listColorDtos = colors.stream()
			.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListColorDto>>(listColorDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListColorDto>> getAllPaged(int pageNo, int pageSize){
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Color> colors = this.colorDao.findAll(pageable).getContent();
		List<ListColorDto> listColorDtos = colors.stream()
			.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListColorDto>>(listColorDtos, Messages.DATAINPAGE +
			Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE + Integer.toString(pageSize));
	}

	@Override
	public Result delete(int colorId){
		
		checkColorIdExists(colorId);
		
		String colorNameBeforeDelete = this.colorDao.findByColorId(colorId).getColorName();
		this.colorDao.deleteById(colorId);
		
		return new SuccessResult(Messages.COLORDELETED + colorNameBeforeDelete);
	}

	private void checkColorNameExists(String colorName){
		
		if (this.colorDao.existsByColorName(colorName)) {
			
			throw new BusinessException(Messages.COLOREXISTS + colorName);
		}
	}

	private void checkColorIdExists(int colorId){
		
		if (!this.colorDao.existsById(colorId)) {
			
			throw new BusinessException(Messages.COLORNOTFOUND);
		}
	}
	
	private void checkPageNoAndPageSize(int pageNo, int pageSize) {
		
		if(pageNo <= 0) {
			
			throw new BusinessException(Messages.PAGENOCANNOTLESSTHANZERO);
		}else if(pageSize <= 0) {
			
			throw new BusinessException(Messages.PAGESIZECANNOTLESSTHANZERO);
		}
	}
}
