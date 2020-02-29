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
@Table(name = "SHOE_TRUE_SIZE_DETAILS")
public class ShoeTrueSizeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="shoe_brand_id")
	private Integer shoeBrandId;

	@Column(name="shoe_size")
	private String shoeSize;

	@Column(name="true_size")
	private Integer trueSize;

	@Override
	public String toString() {
		return "ShoeTrueSizeDetails [id=" + id + ", shoeBrandId=" + shoeBrandId + ", shoeSize=" + shoeSize + ", trueSize="
				+ trueSize + "]";
	}

}
