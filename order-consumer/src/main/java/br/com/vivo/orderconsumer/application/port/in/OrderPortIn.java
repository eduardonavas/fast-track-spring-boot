package br.com.vivo.orderconsumer.application.port.in;

import br.com.vivo.orderconsumer.domain.Order;

public interface OrderPortIn {

	void consume(Order order);
}
