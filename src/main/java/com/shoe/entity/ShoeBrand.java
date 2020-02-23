package com.shoe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="SHOE_CODE_NAME")
public class ShoeBrand {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int shoeId;
	private String shoeBrandName;
	
}
