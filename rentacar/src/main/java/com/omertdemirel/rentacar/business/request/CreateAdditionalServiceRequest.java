package com.omertdemirel.rentacar.business.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	private String additionalServiceName;
	private String additionalServiceDescription;
	private BigDecimal additionalServiceDailyPrice;
}