package br.com.vivo.orderproducer.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivo.orderproducer.application.port.in.OrderPortIn;
import br.com.vivo.orderproducer.application.port.out.OrderEventPortOut;
import br.com.vivo.orderproducer.application.port.out.OrderPortOut;
import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;
import br.com.vivo.orderproducer.domain.dto.OrderDto;
import br.com.vivo.orderproducer.framework.adapter.exception.BusinessException;
import br.com.vivo.orderproducer.framework.adapter.exception.ExceptionEnum;
import br.com.vivo.orderproducer.framework.adapter.exception.InvalidTotalException;
import br.com.vivo.orderproducer.framework.adapter.util.Util;

@Service
public class OrderPortInService implements OrderPortIn{
	
	private static final Long DEFAULT_ID = 0L; 
	
	private static final String MESSAGE_EXCEPTION_VALOR_ZERO = "O total nao pode ser igual a zero";
	private static final String MESSAGE_EXCEPTION_VALOR_NEGATIVO = "O total nao pode ser negativo";
	
	@Autowired
	private OrderPortOut orderPortOut;
	
	@Autowired
	private OrderEventPortOut orderEventPortOut;
		
	@Autowired 
	private Util util;
		
	@Override
	public OrderDto save(OrderDto orderDto) {
		checkOrdertotalValue(orderDto.getTotal());
		orderDto.setId(DEFAULT_ID);
		orderDto.setStatus(StatusEnum.NOT_PROCESSED);
		Order order = orderPortOut.save(util.toOrder(orderDto));
		
		orderEventPortOut.produce(order);
		
		return util.toOrderDto(order);
	}

	@Override
	public List<OrderDto> findAll() {
		
		return orderPortOut.findAll().stream().map(order -> util.toOrderDto(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> search(String maxTotal, String minTotal, StatusEnum status, String q) {
		List<OrderDto> orderDtoList =  orderPortOut.search(maxTotal, minTotal, status, q).stream().map(order -> util.toOrderDto(order)).collect(Collectors.toList());
				
		return orderDtoList;
		
	}

	@Override
	public OrderDto findById(Long id) {
	
		return util.toOrderDto(orderPortOut.findById(id));
	}

	@Override
	public OrderDto update(OrderDto orderDto, Long id) {
		checkOrdertotalValue(orderDto.getTotal());
		findById(id);
		
		orderDto.setId(id);
		
		return util.toOrderDto(orderPortOut.save(util.toOrder(orderDto)));
	}

	@Override
	public OrderDto delete(Long id) {
		
		return util.toOrderDto(orderPortOut.delete(id));
		
	}
	
	private void checkOrdertotalValue(Double total) {
		if(total <= 0) {
			throw new InvalidTotalException(total < 0 ? MESSAGE_EXCEPTION_VALOR_NEGATIVO : MESSAGE_EXCEPTION_VALOR_ZERO);
		}
	}
}
