package br.com.vivo.orderconsumer.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivo.orderconsumer.application.port.in.OrderPortIn;
import br.com.vivo.orderconsumer.application.port.out.OrderPortOut;
import br.com.vivo.orderconsumer.domain.Order;
import br.com.vivo.orderconsumer.domain.StatusEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderPortInImpl implements OrderPortIn{

	@Autowired
	private OrderPortOut orderPortOut;
	
	@Override
	public void consume(Order order) {
						
		order.setStatus(StatusEnum.PROCESSED);
		
		log.info("Order Status: {}", order.getStatus());
		
		orderPortOut.update(order);
	}
}
