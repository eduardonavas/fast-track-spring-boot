package br.com.vivo.orderproducer.framework.adapter.exception;

public class InvalidTotalException extends RuntimeException {

	
	private static final long serialVersionUID = 6410356501648484697L;

	public InvalidTotalException(String message) {
		super(message);
	}
}
