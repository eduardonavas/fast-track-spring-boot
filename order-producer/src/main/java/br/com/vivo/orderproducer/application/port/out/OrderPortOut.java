package br.com.vivo.orderproducer.application.port.out;

import java.util.List;

import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;

public interface OrderPortOut {

	Order save(Order order);
	List<Order> findAll();
	List<Order> search(String maxTotal, String minTotal, StatusEnum status, String q);
	Order findById(Long id);
	Order delete(Long id);
}
