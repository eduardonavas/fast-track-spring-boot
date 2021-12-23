package br.com.vivo.orderconsumer.framework.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivo.orderconsumer.application.port.out.OrderPortOut;
import br.com.vivo.orderconsumer.domain.Order;
import br.com.vivo.orderconsumer.framework.adapter.exception.OrderNotFoundException;
import br.com.vivo.orderconsumer.framework.adapter.out.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersistOrder implements OrderPortOut{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void update(Order order) {
		
		try {
			findById(order.getId());
			orderRepository.save(order);
			log.info("Order id: {} saved", order.getId());
		} catch (OrderNotFoundException e) {
			log.error("Error",e);
		}
	}
	
	private Order findById(Long id) throws OrderNotFoundException {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order id \"%d\" not found", id)));
	}
}
