package com.omertdemirel.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.omertdemirel.rentacar.business.dtos.InvoiceDto;
import com.omertdemirel.rentacar.business.dtos.ListInvoiceDto;
import com.omertdemirel.rentacar.business.request.CreateInvoiceRequest;
import com.omertdemirel.rentacar.business.request.UpdateInvoiceRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface InvoiceService {

	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	
	Result create(CreateInvoiceRequest createInvoiceRequest);
	
	Result delete(int invoiceId);
	
	DataResult<List<ListInvoiceDto>> listAll();
	
	DataResult<InvoiceDto> getByInvoiceId(int invoiceId);
	
	DataResult<InvoiceDto> getByInvoiceNumber(String invoiceNumber);
	
	DataResult<List<ListInvoiceDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListInvoiceDto>> getByDateofBetween(LocalDate startDate, LocalDate finishDate);
	
	DataResult<List<ListInvoiceDto>> getInvoiceByCustomerId(int customerId);
	
}
