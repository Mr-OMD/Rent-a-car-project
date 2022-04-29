package com.omertdemirel.rentacar.business.request;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

	private int userId;
	
	@Email
	private String email;
	
	private String password;
}
