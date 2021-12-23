package br.com.vivo.orderconsumer.framework.adapter.in.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.vivo.orderconsumer.application.port.in.OrderPortIn;
import br.com.vivo.orderconsumer.domain.Order;

@Service
public class OrderConsumer {

	@Autowired
	private OrderPortIn orderPortIn;
	
	@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}", containerFactory = "orderConsumerContainer")
	public void consumer(@Payload Order order) {
		orderPortIn.consume(order);
	}

}
