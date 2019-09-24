package com.book.filter;

public enum BillingEnumCodes {

	USERNAME("admin"), PASSWORD("admin"), MESSAGE("No data found."), FLAG("Success"), HEADER("Authorization");

	private String code;

	private BillingEnumCodes(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
