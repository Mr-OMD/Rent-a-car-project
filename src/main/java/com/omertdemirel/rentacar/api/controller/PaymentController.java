package com.omertdemirel.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.PaymentService;
import com.omertdemirel.rentacar.business.dtos.ListPaymentDto;
import com.omertdemirel.rentacar.business.dtos.PaymentDto;
import com.omertdemirel.rentacar.business.request.CreatePaymentRequest;
import com.omertdemirel.rentacar.business.request.CreatePaymentWithSavedCardRequest;
import com.omertdemirel.rentacar.business.request.UpdatePaymentRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {

		this.paymentService = paymentService;
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
		
		return this.paymentService.update(updatePaymentRequest);
	}
	
	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreatePaymentRequest createPaymentRequest, boolean saveCard) {
		
		return this.paymentService.create(createPaymentRequest, saveCard);
	}
	
	@PostMapping("/createWithSavedCard")
	public Result createWithSavedCard(CreatePaymentWithSavedCardRequest createPaymentWithSavedCardRequest) {
		
		return this.paymentService.createWithSavedCard(createPaymentWithSavedCardRequest);
	}
	
	@GetMapping("/listAll")
	public DataResult<List<ListPaymentDto>> listAll(){
		
		return this.paymentService.listAll();
	}
	
	@GetMapping("/getById")
	public DataResult<PaymentDto> getById(@RequestParam int paymentId){
		
		return this.paymentService.getByPaymentId(paymentId);
	}
	
	@GetMapping("/getAllSorted")
	public DataResult<List<ListPaymentDto>> getAllSorted(Sort.Direction direction){
		
		return this.paymentService.getAllSorted(direction);
	}
	
	@GetMapping("/getAllPaged")
	public DataResult<List<ListPaymentDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.paymentService.getAllPaged(pageNo, pageSize);
	}
	
	public Result delete(@RequestParam int paymentId) {
		
		return this.paymentService.delete(paymentId);
	}
}
