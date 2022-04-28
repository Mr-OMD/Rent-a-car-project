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
public class CreateIndividualCustomerRequest {

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 11, max = 11)
	private String nationalIdentity;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 3, max = 50)
	private String firstname;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 50)
	private String lastname;

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
