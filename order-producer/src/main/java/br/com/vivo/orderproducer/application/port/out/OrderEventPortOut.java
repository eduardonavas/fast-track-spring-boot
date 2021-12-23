package br.com.vivo.orderproducer.application.port.out;

import br.com.vivo.orderproducer.domain.Order;

public interface OrderEventPortOut {

	void produce(Order order);
}
