package com.shoe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
public class ShoeResponse {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int brandId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brandName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String shoeSize;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private double avgTrueSize;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String status;

	@Override
	public String toString() {
		return "ShoeResponse [brandId=" + brandId + ", brandName=" + brandName + ", shoeSize=" + shoeSize
				+ ", avgTrueSize=" + avgTrueSize + ", status=" + status + "]";
	}
	
	
}
