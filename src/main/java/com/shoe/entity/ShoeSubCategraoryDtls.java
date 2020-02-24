package com.shoe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SHOE_SUB_CAT_DTLS")
public class ShoeSubCategraoryDtls {

	@Id
	private int shoeSubCatId;
	private int shoeId;
	private String shoeType;
	private String shoeName;

	@Override
	public String toString() {
		return "ShoeSubCategraoryDtls [shoeSubCatId=" + shoeSubCatId + ", shoeId=" + shoeId + ", shoeType=" + shoeType
				+ ", shoeName=" + shoeName + "]";
	}

}
