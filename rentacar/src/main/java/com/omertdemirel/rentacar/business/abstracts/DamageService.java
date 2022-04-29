package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.DamageDto;
import com.omertdemirel.rentacar.business.dtos.ListDamageDto;
import com.omertdemirel.rentacar.business.request.CreateDamageRequest;
import com.omertdemirel.rentacar.business.request.UpdateDamageRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface DamageService {

	Result update(UpdateDamageRequest updateDamageRequest);

	Result create(CreateDamageRequest createDamageRequest);

	Result delete(int damageId);

	DataResult<List<ListDamageDto>> listAll();

	DataResult<DamageDto> getById(int damageId);

	DataResult<List<ListDamageDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize);

}
