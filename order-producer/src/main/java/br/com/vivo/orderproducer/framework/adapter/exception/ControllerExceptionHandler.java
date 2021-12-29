package br.com.vivo.orderproducer.framework.adapter.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<ExceptionModel> bussinesException(BusinessException e){
		
		ExceptionModel exceptionModel = ExceptionModel.builder()
				.message(e.getMessage())
				.statusCode(e.getCode())
				.build();
				
		return new ResponseEntity<>(exceptionModel, null, exceptionModel.getStatusCode());
	}
	
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ExceptionModel> bussinesException(MethodArgumentNotValidException e){
		ExceptionModel exceptionModel = ExceptionModel.builder()
				.message(e.getFieldError() == null ? e.getMessage() : e.getFieldError().getDefaultMessage())
				.statusCode(400)
				.build();		
		
		return new ResponseEntity<>(exceptionModel, null,  exceptionModel.getStatusCode());
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<ExceptionModel> bussinesException(HttpMessageNotReadableException e){
		ExceptionModel exceptionModel = ExceptionModel.builder()
				.message(e.getMessage())
				.statusCode(400)
				.build();		
		
		return new ResponseEntity<>(exceptionModel, null, exceptionModel.getStatusCode());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ExceptionModel> bussinesException(Exception e){
		ExceptionModel exceptionModel = ExceptionModel.builder()
				.message(e.getMessage())
				.statusCode(500)
				.build();		
		
		return new ResponseEntity<>(exceptionModel, null,  exceptionModel.getStatusCode());
	}
}
