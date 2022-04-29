package com.omertdemirel.rentacar.business.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListBrandDto {

	private int brandId;
	private String brandName;
	private List<ListCarDto> cars;
}