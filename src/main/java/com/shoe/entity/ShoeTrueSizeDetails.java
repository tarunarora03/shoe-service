package com.shoe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SHOE_TRUE_SIZE_DETAILS")
public class ShoeTrueSizeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int shoeBrandId;
	private String shoeSize;
	private int trueSize;

	@Override
	public String toString() {
		return "ShoeTrueSizeDetails [id=" + id + ", shoeBrandId=" + shoeBrandId + ", shoeSize=" + shoeSize + ", trueSize="
				+ trueSize + "]";
	}

}
