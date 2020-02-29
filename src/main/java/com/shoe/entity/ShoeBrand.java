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
@Table(name = "SHOE_BRAND_DTLS")
public class ShoeBrand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "shoe_brand_name")
	private String shoeBrandName;

	@Override
	public String toString() {
		return "ShoeBrand [shoeId=" + id + ", shoeBrandName=" + shoeBrandName + "]";
	}

}
