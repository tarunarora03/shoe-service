package com.shoe.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private int status;
	private String error;
}