package br.com.vivo.orderconsumer.framework.adapter.exception;

public class OrderNotFoundException extends Exception {
	private static final long serialVersionUID = -6843527165431060852L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
