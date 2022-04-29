package com.omertdemirel.rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id")
public class IndividualCustomer extends Customer {
	
	@Column(name = "national_id", unique = true)
	private String nationalId;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;
}
