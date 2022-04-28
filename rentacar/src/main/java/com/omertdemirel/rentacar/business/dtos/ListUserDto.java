package com.omertdemirel.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUserDto {

	private int userId;
	
	private String email;
	
	private String password;
}
