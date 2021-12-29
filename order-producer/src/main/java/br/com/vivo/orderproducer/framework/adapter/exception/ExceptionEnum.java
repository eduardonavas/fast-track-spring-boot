package br.com.vivo.orderproducer.framework.adapter.exception;

import lombok.Getter;


@Getter
public enum ExceptionEnum {

	UNAUTHORIZED("401","not authorized"),
	FORBIDDEN("403","forbidden transaction"),
	NOT_FOUND("404","order not found"),
	NO_CONTENT("204","No content");
	
	private String code;
	private String message;
	
	
	private ExceptionEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
