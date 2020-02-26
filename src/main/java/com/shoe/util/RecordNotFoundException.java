package com.shoe.util;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String error, String id) {
		super(error+ ": " + id);
	}
}