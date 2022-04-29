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

import com.omertdemirel.rentacar.business.abstracts.CreditCardService;
import com.omertdemirel.rentacar.business.abstracts.CustomerService;
import com.omertdemirel.rentacar.business.constants.Messages;
import com.omertdemirel.rentacar.business.dtos.CreditCardDto;
import com.omertdemirel.rentacar.business.dtos.ListCreditCardDto;
import com.omertdemirel.rentacar.business.request.CreateCreditCardRequest;
import com.omertdemirel.rentacar.business.request.UpdateCreditCardRequest;
import com.omertdemirel.rentacar.core.utilities.exceptions.BusinessException;
import com.omertdemirel.rentacar.core.utilities.mapping.ModelMapperService;
import com.omertdemirel.rentacar.core.utilities.posServiceAdapter.abstracts.PosService;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;
import com.omertdemirel.rentacar.core.utilities.results.SuccessDataResult;
import com.omertdemirel.rentacar.core.utilities.results.SuccessResult;
import com.omertdemirel.rentacar.dataAccess.abstracts.CreditCardDao;
import com.omertdemirel.rentacar.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService{
	
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private PosService posService;
	
	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService, CustomerService customerService, PosService posService) {

		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.posService = posService;
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		
		checkCreditCardIdExists(updateCreditCardRequest.getCreditCardId());
		checkCreditCardNumberExists(updateCreditCardRequest.getCreditCardNumber());
		this.customerService.checkCustomerExists(updateCreditCardRequest.getCustomerId());
		
		CreditCard creditCard = this.modelMapperService.forRequest()
			.map(updateCreditCardRequest, CreditCard.class);
		creditCard.setCreditCardCustomer(this.customerService.setByCustomerId(updateCreditCardRequest.getCustomerId()));
		checkCreditCardIsValid(creditCard);
		this.creditCardDao.save(creditCard);
		
		return new SuccessDataResult<UpdateCreditCardRequest>(updateCreditCardRequest,
			Messages.CREDITCARDUPDATED);
	}

	@Override
	@Transactional
	public Result create(CreateCreditCardRequest createCreditCardRequest) {
		
		checkCreditCardNumberAlreadyExists(createCreditCardRequest.getCreditCardNumber());
		this.customerService.checkCustomerExists(createCreditCardRequest.getCustomerId());		
		
		CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		creditCard.setCreditCardCustomer(this.customerService.setByCustomerId(createCreditCardRequest.getCustomerId()));
		checkCreditCardIsValid(creditCard);
		this.creditCardDao.save(creditCard);
		
		return new SuccessDataResult<CreateCreditCardRequest>(createCreditCardRequest,
			Messages.CREDITCARDADDED);
	}

	@Override
	public DataResult<List<ListCreditCardDto>> listAll() {
		
		Sort sort = Sort.by(Direction.ASC, "creditCardId");
		List<CreditCard> creditCards = this.creditCardDao.findAll(sort);
		List<ListCreditCardDto> listCreditCardDtos = creditCards.stream()
			.map(creditCard -> this.modelMapperService.forDto().map(creditCard, ListCreditCardDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDto>>(listCreditCardDtos, Messages.CREDITCARDSLISTED);
	}

	@Override
	public DataResult<CreditCardDto> getById(int creditCardId) {
		
		checkCreditCardIdExists(creditCardId);
		
		CreditCard creditCard = this.creditCardDao.getByCreditCardId(creditCardId);
		CreditCardDto creditCardDto = this.modelMapperService.forDto().map(creditCard, CreditCardDto.class);
		
		return new SuccessDataResult<CreditCardDto>(creditCardDto, Messages.CREDITCARDGETTEDBYID);
	}

	@Override
	public DataResult<CreditCardDto> getByCreditCardNumber(String creditCardNumber) {
		
		checkCreditCardNumberExists(creditCardNumber);
		
		CreditCard creditCard = this.creditCardDao.getByCreditCardNumber(creditCardNumber);
		CreditCardDto creditCardDto = this.modelMapperService.forDto().map(creditCard, CreditCardDto.class);
		
		return new SuccessDataResult<CreditCardDto>(creditCardDto,
			Messages.CREDITCARDGETTEDBYCREDITCARDNUMBER);
	}

	@Override
	public DataResult<List<ListCreditCardDto>> getAllSorted(Direction direction) {
		
		Sort sort = Sort.by(direction, "creditCardOwnerName");
		List<CreditCard> creditCards = this.creditCardDao.findAll(sort);
		List<ListCreditCardDto> listCreditCardDtos = creditCards.stream()
			.map(creditCard -> this.modelMapperService.forDto().map(creditCard, ListCreditCardDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDto>>(listCreditCardDtos,
			Messages.DATALISTEDIN + direction.toString() + Messages.ORDER);
	}

	@Override
	public DataResult<List<ListCreditCardDto>> getAllPaged(int pageNo, int pageSize) {
		
		checkPageNoAndPageSize(pageNo, pageSize);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CreditCard> creditCards = this.creditCardDao.findAll(pageable).getContent();
		List<ListCreditCardDto> listCreditCardDtos = creditCards.stream()
			.map(creditCard -> this.modelMapperService.forDto().map(creditCard, ListCreditCardDto.class))
			.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDto>>(listCreditCardDtos,
			Messages.DATAINPAGE + Integer.toString(pageNo) + Messages.ISLISTEDWITHDATASIZE
			+ Integer.toString(pageSize));
	}

	@Override
	public Result delete(int creditCardId) {
		
		checkCreditCardIdExists(creditCardId);
		
		this.creditCardDao.deleteById(creditCardId);
		
		return new SuccessResult(Messages.CREDITCARDDELETED);
	}
	
	private void checkCreditCardIdExists(int creditCardId) {
		
		if(!this.creditCardDao.existsById(creditCardId)) {
			
			throw new BusinessException(Messages.CREDITCARDNOTFOUND);
		}
	}
	
	private void checkCreditCardNumberExists(String creditCardNumber) {
		
		if(!this.creditCardDao.existsByCreditCardNumber(creditCardNumber)) {
			
			throw new BusinessException(Messages.CREDITCARDNOTFOUND);
		}
	}
	
	private void checkCreditCardNumberAlreadyExists(String creditCardNumber) {
		
		if(this.creditCardDao.existsByCreditCardNumber(creditCardNumber)) {
			
			throw new BusinessException(Messages.CREDITCARDISEXISTS);
		}
	}
	
	private void checkPageNoAndPageSize(int pageNo, int pageSize) {
		
		if(pageNo <= 0) {
			
			throw new BusinessException(Messages.PAGENOCANNOTLESSTHANZERO);
		}else if(pageSize <= 0) {
			
			throw new BusinessException(Messages.PAGESIZECANNOTLESSTHANZERO);
		}
	}
	
	private void checkCreditCardIsValid(CreditCard creditCard) {
		
		if(!this.posService.checkCardIsActive(creditCard)) {
			
			throw new BusinessException(Messages.CREDITCARDNOTVALID);
		}
	}
}
