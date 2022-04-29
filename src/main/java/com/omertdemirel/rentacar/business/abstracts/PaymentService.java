package com.omertdemirel.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.omertdemirel.rentacar.business.dtos.ListPaymentDto;
import com.omertdemirel.rentacar.business.dtos.PaymentDto;
import com.omertdemirel.rentacar.business.request.CreatePaymentRequest;
import com.omertdemirel.rentacar.business.request.CreatePaymentWithSavedCardRequest;
import com.omertdemirel.rentacar.business.request.UpdatePaymentRequest;
import com.omertdemirel.rentacar.core.utilities.results.DataResult;
import com.omertdemirel.rentacar.core.utilities.results.Result;

public interface PaymentService {

	Result update(UpdatePaymentRequest updatePaymentRequest);

	Result create(CreatePaymentRequest createPaymentRequest, boolean saveCard);

	Result delete(int paymentId);

	Result createWithSavedCard(CreatePaymentWithSavedCardRequest createPaymentWithSavedCardRequest);

	DataResult<List<ListPaymentDto>> listAll();

	DataResult<PaymentDto> getByPaymentId(int paymentId);

	DataResult<List<ListPaymentDto>> getAllSorted(Sort.Direction direction);

	DataResult<List<ListPaymentDto>> getAllPaged(int pageNo, int pageSize);

}
