package com.omertdemirel.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omertdemirel.rentacar.business.abstracts.CorporateCustomerService;
import com.omertdemirel.rentacar.business.abstracts.CustomerService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.CorporateCustomerDto;
import com.omertdemirel.rentacar.business.dtos.ListCorporateCustomerDto;
import com.omertdemirel.rentacar.business.request.CreateCorporateCustomerRequest;
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
	private CustomerService customerService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService,
			CustomerService customerService) {
		
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest){

		checkCorporateCustomerIdExists(updateCorporateCustomerRequest.getUserId());
		this.customerService.checkEmailExists(updateCorporateCustomerRequest.getEmail());
		
		CorporateCustomerDto corporateCustomerTemp = getByCorporateCustomerId(updateCorporateCustomerRequest.getUserId()).getData();
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
			.map(updateCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomer.setRegisteredDate(corporateCustomerTemp.getRegisteredDate());
		corporateCustomer.setTaxNo(corporateCustomerTemp.getTaxNo());
		this.corporateCustomerDao.save(corporateCustomer);
		 
		
		return new SuccessDataResult<UpdateCorporateCustomerRequest>(updateCorporateCustomerRequest,
			Messages.CORPORATECUSTOMERUPDATED + corporateCustomer.getCorporateName());
	}

	@Override
	@Transactional
	public Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest){
		
		LocalDate date = LocalDate.now();
		
		checkCorporateTaxNoContainsLetter(createCorporateCustomerRequest.getTaxNo());
		checkCorporateNameExists(createCorporateCustomerRequest.getCorporateName());
		checkTaxNoExists(createCorporateCustomerRequest.getTaxNo());
		this.customerService.checkEmailExists(createCorporateCustomerRequest.getEmail());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
			.map(createCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomer.setRegisteredDate(date);
		this.corporateCustomerDao.save(corporateCustomer);

		return new SuccessDataResult<CreateCorporateCustomerRequest>(createCorporateCustomerRequest,
			Messages.CORPORATECUSTOMERADDED + corporateCustomer.getCorporateName());
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> listAll(){
		
		Sort sort = Sort.by(Direction.ASC, "userId");
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(sort);
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream()
			.map(corporateCustomer -> this.modelMapperService.forDto()
			.map(corporateCustomer, ListCorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos,
			Messages.CORPORATECUSTOMERSLISTED);
	}

	@Override
	public DataResult<CorporateCustomerDto> getByCorporateCustomerId(int corporateCustomerId){
		
		checkCorporateCustomerIdExists(corporateCustomerId);
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);
		CorporateCustomerDto corporateCustomerDto = this.modelMapperService.forDto()
			.map(corporateCustomer, CorporateCustomerDto.class);
		
		return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto,
			Messages.CORPORATECUSTOMERGETTEDBYID);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllSorted(Direction direction){
		
		Sort sort = Sort.by(direction, "corporateName");
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(sort);
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream()
			.map(corporateCustomer -> this.modelMapperService.forDto()
			.map(corporateCustomer, ListCorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize){
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(pageable)
			.getContent();
		List<ListCorporateCustomerDto> listAddiCorporateCustomerDtos = corporateCustomers.stream()
			.map(corporateCustomer -> this.modelMapperService.forDto()
			.map(corporateCustomers,ListCorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listAddiCorporateCustomerDtos,
			Messages.DATAINPAGE + Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE
			+ Integer.toString(pageSize));
	}

	@Override
	public Result delete(int corporateCustomerId){
		
		checkCorporateCustomerIdExists(corporateCustomerId);
		
		String corporateNameBefore = this.corporateCustomerDao.findById(corporateCustomerId)
			.get().getCorporateName();
		this.corporateCustomerDao.deleteById(corporateCustomerId);
		
		return new SuccessResult(Messages.CORPORATECUSTOMERDELETED + corporateNameBefore);
	}

	private void checkCorporateNameExists(String corporateName){
		
		if (this.corporateCustomerDao.existsByCorporateName(corporateName)) {
			
			throw new BusinessException(Messages.CORPORATECUSTOMEREXISTS + corporateName);
		}
	}

	private void checkCorporateCustomerIdExists(int corporateCustomerId){
		
		if (!this.corporateCustomerDao.existsById(corporateCustomerId)) {
			
			throw new BusinessException(Messages.CORPORATECUSTOMERNOTFOUND);
		}
	}

	private void checkTaxNoExists(String taxNo){
		
		if (this.corporateCustomerDao.existsByTaxNo(taxNo)) {
			
			throw new BusinessException(Messages.CORPORATECUSTOMERTAXNOEXISTS + taxNo);
		}
	}
	
	private void checkPageNoAndPageSize(int pageNo, int pageSize) {
		
		if(pageNo <= 0) {
			
			throw new BusinessException(Messages.PAGENOCANNOTLESSTHANZERO);
		}else if(pageSize <= 0) {
			
			throw new BusinessException(Messages.PAGESIZECANNOTLESSTHANZERO);
		}
	}
	
	private void checkCorporateTaxNoContainsLetter(String taxNo) {
		
		boolean result = taxNo.matches("[0-9]+");
		
		if(!result) {
			
			throw new BusinessException(Messages.CORPORATECUSTOMERTAXNOCONTAINSLETTER);
		}
	}
}
