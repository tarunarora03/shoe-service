package com.shoe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SHOE_DETAILS")
public class ShoeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shoeSubCatId;
	private int shoeId;
	private double shoeSize;
	private double avg;
	private int count;

}
