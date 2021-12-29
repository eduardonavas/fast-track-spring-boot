package br.com.vivo.orderproducer.framework.adapter.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionModel {

	@JsonProperty("status_code")
	private Integer statusCode;
	private String message;
	
}
