package com.shoe.entity;

import javax.persistence.Column;
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
	private Integer id;

	@Column(name="shoe_brand_id")
	private Integer shoeBrandId;

	@Column(name="shoe_size")
	private String shoeSize;

	@Column(name="shoe_sub_cat_id")
	private Integer shoeSubCatId;

	@Column(name = "TRUE_SIZE_AVG")
	private double trueSizeAvg;
	
	@Column(name="true_size_count")
	private Integer trueSizeCount;

	@Override
	public String toString() {
		return "ShoeDetails [Id=" + id + ", shoeSubCatId=" + shoeSubCatId + ", shoeBrandId=" + shoeBrandId + ", shoeSize="
				+ shoeSize + ", trueSizeAvg=" + trueSizeAvg + ", trueSizeCount=" + trueSizeCount + "]";
	}

	
}
