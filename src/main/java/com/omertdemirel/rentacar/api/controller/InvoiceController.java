package com.omertdemirel.rentacar.api.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omertdemirel.rentacar.business.abstracts.InvoiceService;
import com.omertdemirel.rentacar.business.dtos.InvoiceDto;
import com.omertdemirel.rentacar.business.dtos.ListInvoiceDto;
import com.omertdemirel.rentacar.business.request.CreateInvoiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateInvoiceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {

		this.invoiceService = invoiceService;
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
		
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
		
		return this.invoiceService.create(createInvoiceRequest);
	}
	
	@GetMapping("/listAll")
	public DataResult<List<ListInvoiceDto>> listAll(){
		
		return this.invoiceService.listAll();
	}
	
	@GetMapping("/getById")
	public DataResult<InvoiceDto> getByInvoiceNumber(@RequestParam int invoiceId){
		
		return this.invoiceService.getByInvoiceId(invoiceId);
	}
	
	@GetMapping("/getByInvoiceNumber")
	public DataResult<InvoiceDto> getByInvoiceNo(@RequestParam String invoiceNumber){
		
		return this.invoiceService.getByInvoiceNumber(invoiceNumber);
	}
	
	@GetMapping("/getAllPaged")
	public DataResult<List<ListInvoiceDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.invoiceService.getAllPaged(pageNo, pageSize);
	}
	
	@GetMapping("/getByDateOfBetween")
	public DataResult<List<ListInvoiceDto>> getByDateOfBetween(@DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
			@DateTimeFormat(iso = ISO.DATE) LocalDate finishDate){
		
		return this.invoiceService.getByDateofBetween(startDate, finishDate);
	}
	
	@GetMapping("/getInvoiceByCustomerId")
	public DataResult<List<ListInvoiceDto>> getInvoiceByCustomerId(int customerId){
		
		return this.invoiceService.getInvoiceByCustomerId(customerId);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int invoiceId) {
		
		return this.invoiceService.delete(invoiceId);
	}
}
