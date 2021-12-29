package br.com.vivo.orderproducer.framework.adapter.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivo.orderproducer.application.port.out.OrderPortOut;
import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;
import br.com.vivo.orderproducer.framework.adapter.exception.BusinessException;
import br.com.vivo.orderproducer.framework.adapter.exception.ExceptionEnum;
import br.com.vivo.orderproducer.framework.adapter.out.repository.OrderRepository;

@Service
public class PersistOrder implements OrderPortOut {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order save(Order order) {
		order = orderRepository.save(order);
		return order;
		
	}
	
	@Override
	public List<Order> findAll() {
		
		return orderRepository.findAll();
	}

	@Override
	public List<Order> search(String maxTotal, String minTotal, StatusEnum status, String q) {
		Double max = maxTotal == null ? null : Double.parseDouble(maxTotal);
		Double min = minTotal == null ? null : Double.parseDouble(minTotal);
		
		return orderRepository.search(max, min, status, q);
		
	}

	@Override
	public Order findById(Long id) {

		
		return orderRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionEnum.NOT_FOUND));
	}

	@Override
	public Order delete(Long id) {
		Order order = findById(id);
		orderRepository.delete(order);
		
		return order;
	}
}
