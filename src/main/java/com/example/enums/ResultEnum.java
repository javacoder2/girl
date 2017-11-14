package com.example.enums;

public enum ResultEnum {
	UNKOWN_ERROR(-1, "未知错误"),
	SUCCESS(-1, "未知错误"),
	PRIMARY_SCHOOL(100, "您可能还在上小学"),
	MIDDLE_SCHOOL(101, "您可能还在上初中") ;
	
	private Integer code;

	private String msg;

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
