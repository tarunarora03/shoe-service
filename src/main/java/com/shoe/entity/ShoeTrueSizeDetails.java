package com.shoe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SHOE_TRUE_SIZE_DETAILS")
public class ShoeTrueSizeDetails {

	@Id
	private int id;
	private int shoeId;
	private double shoeSize;
	private int size;
}
