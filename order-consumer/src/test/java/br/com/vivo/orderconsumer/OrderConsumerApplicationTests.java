package br.com.vivo.orderconsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.vivo.orderconsumer.application.port.in.OrderPortIn;
import br.com.vivo.orderconsumer.domain.Order;
import br.com.vivo.orderconsumer.domain.StatusEnum;
import br.com.vivo.orderconsumer.framework.adapter.out.repository.OrderRepository;

@SpringBootTest
class OrderConsumerApplicationTests {

	@MockBean
	OrderRepository orderRepository;
	
	@Autowired
	OrderPortIn orderPortIn;
	
	@Test
	void orderConsumeTest() {
		Order order = new Order();
		order.setName("order teste");
		order.setDescription("order description teste");
		order.setStatus(StatusEnum.NOT_PROCESSED);
		order.setTotal(2000d);
		
		BDDMockito.given(orderRepository.save(any())).willReturn(order);
		BDDMockito.given(orderRepository.findById(any())).willReturn(Optional.of(order));
		
		orderPortIn.consume(order);
		
		assertEquals(StatusEnum.PROCESSED, order.getStatus());		
		
	}
}
