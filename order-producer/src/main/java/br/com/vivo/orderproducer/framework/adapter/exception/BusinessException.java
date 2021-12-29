package br.com.vivo.orderproducer.framework.adapter.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3679142712655242849L;
	
	public BusinessException(ExceptionEnum e) {
		super(e.getMessage());
		this.code = Integer.parseInt(e.getCode());
	}
	
	private final Integer code;
}
