package com.omertdemirel.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.omertdemirel.rentacar.business.abstracts.CorporateCustomerService;
import com.omertdemirel.rentacar.business.dtos.CorporateCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListCorporateCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.DeleteCorporateCustomerRequest;
import com.omertdemirel.rentacar.business.request.UpdateCorporateCustomerRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CorporateCustomerDao;
import com.omertdemirel.rentacar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<CorporateCustomerDto> getById(int id) throws BusinessException {

		CorporateCustomer corporateCustomer = corporateCustomerDao.getById(id);
		CorporateCustomerDto corporateCustomerDto = modelMapperService.forDto().map(corporateCustomer,
				CorporateCustomerDto.class);
		return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto);
	}

	@Override
	public Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {

		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomerDao.save(corporateCustomer);

		return new SuccessDataResult<CreateCorporateCustomerRequest>();
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {

		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomerDao.save(corporateCustomer);

		return new SuccessDataResult<UpdateCorporateCustomerRequest>();
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllSorted(Direction direction) throws BusinessException {

		Sort sort = Sort.by(direction, "corporateName");
		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll(sort);
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll(pageable).getContent();
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomers,
						ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException {

		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll();
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos);
	}

}
