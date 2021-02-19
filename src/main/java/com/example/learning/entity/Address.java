package com.example.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(max = 100)
	@Column(name="addressline")
	private String addressLine;

	@NotNull
	@Size(max = 100)
	private String city;

	@NotNull
	@Size(max = 100)
	private String state;

	@NotNull
	@Size(max = 6)
	@Column(name="zipcode")
	private String zipCode;
}
