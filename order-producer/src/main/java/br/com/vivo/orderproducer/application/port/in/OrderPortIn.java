package br.com.vivo.orderproducer.application.port.in;

import java.util.List;

import br.com.vivo.orderproducer.domain.StatusEnum;
import br.com.vivo.orderproducer.domain.dto.OrderDto;

public interface OrderPortIn {

	OrderDto save(OrderDto orderDto);
	List<OrderDto> findAll();
	List<OrderDto> search(String maxTotal, String minTotal, StatusEnum status, String q);
	OrderDto findById(Long id);
	OrderDto update(OrderDto orderDto, Long id);
	OrderDto delete(Long id);
	
}
