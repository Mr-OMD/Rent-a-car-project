package com.omertdemirel.rentacar.business.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 50)
	private String corporateName;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 10, max = 10)
	private String taxNo;

	@NotNull
	@NotEmpty
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
}
