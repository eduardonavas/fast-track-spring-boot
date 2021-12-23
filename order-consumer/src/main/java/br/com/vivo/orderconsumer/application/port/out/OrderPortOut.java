package br.com.vivo.orderconsumer.application.port.out;

import br.com.vivo.orderconsumer.domain.Order;

public interface OrderPortOut {

	void update(Order order);
}
