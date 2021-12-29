package br.com.vivo.orderproducer.framework.adapter.out.kafka;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.vivo.orderproducer.application.port.out.OrderEventPortOut;
import br.com.vivo.orderproducer.domain.Order;


@Service
public class SendOrderEvent implements OrderEventPortOut {

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@Value("${spring.kafka.producer.topic}")
	private String topicName;
	
	public void produce(Order order) {
		kafkaTemplate.send(producer(order));
	}
	
	private ProducerRecord<String, Order> producer(Order order){
		final String KEY = UUID.randomUUID().toString();
		
		return new ProducerRecord<>(topicName, KEY, order);
	}
}
