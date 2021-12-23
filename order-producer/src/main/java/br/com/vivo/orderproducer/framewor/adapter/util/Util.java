package br.com.vivo.orderproducer.framewor.adapter.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.dto.OrderDto;

@Component
public class Util {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Order toOrder(OrderDto orderDto) {
		return modelMapper.map(orderDto, Order.class);
	}
	
	public OrderDto toOrderDto(Order order) {
		return modelMapper.map(order, OrderDto.class);
	}
}
