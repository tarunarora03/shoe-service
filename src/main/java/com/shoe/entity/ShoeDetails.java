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
	private int Id;
	private int shoeSubCatId;
	private int shoeId;
	private String shoeSize;
	private double trueSizeAvg;
	private int trueSizeCount;

	@Override
	public String toString() {
		return "ShoeDetails [Id=" + Id + ", shoeSubCatId=" + shoeSubCatId + ", shoeId=" + shoeId + ", shoeSize="
				+ shoeSize + ", trueSizeAvg=" + trueSizeAvg + ", trueSizeCount=" + trueSizeCount + "]";
	}

	
}
