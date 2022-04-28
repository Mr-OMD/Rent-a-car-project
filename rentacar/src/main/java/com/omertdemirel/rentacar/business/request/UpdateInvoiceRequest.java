package com.omertdemirel.rentacar.business.request;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	@NotNull
	private int invoiceId;
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private int paymentId;
}
