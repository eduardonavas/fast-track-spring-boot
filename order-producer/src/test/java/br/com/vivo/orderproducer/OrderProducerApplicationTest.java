package br.com.vivo.orderproducer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vivo.orderproducer.application.port.out.OrderEventPortOut;
import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;
import br.com.vivo.orderproducer.domain.dto.OrderDto;
import br.com.vivo.orderproducer.framework.adapter.exception.BusinessException;
import br.com.vivo.orderproducer.framework.adapter.in.rest.OrderController;
import br.com.vivo.orderproducer.framework.adapter.out.repository.OrderRepository;

@SpringBootTest
class OrderProducerApplicationTest {

	
	OrderDto orderDtoReference;
	
	OrderDto orderDto;
	
	@MockBean
	OrderRepository orderRepository;
	
	@MockBean
	OrderEventPortOut orderEventPortOut;
	
	@Autowired
	OrderController orderController;
	
	public OrderProducerApplicationTest() throws JsonMappingException, JsonProcessingException {
		
		orderDtoReference = new OrderDto();
		
		orderDtoReference.setName("teste name");
		orderDtoReference.setDescription("teste description");
		orderDtoReference.setStatus(StatusEnum.PROCESSED);
		orderDtoReference.setTotal(500d);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		orderDto = objectMapper.readValue(objectMapper.writeValueAsString(orderDtoReference), OrderDto.class);
	}
	
	@Test
	void saveOrderTest() {
		ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
				
		BDDMockito.given(orderRepository.save(any())).willReturn(buildOrderReturn(orderDto));
			
		Mockito.doNothing().when(orderEventPortOut).produce(any());
				
		ResponseEntity<OrderDto> response = orderController.save(orderDto);
		
		verify(orderRepository).save(captor.capture());
		StatusEnum resultEnum = captor.getValue().getStatus();
			
		assertEquals(orderDto.getName(), response.getBody().getName());
		assertEquals(orderDto.getDescription(), response.getBody().getDescription());
		assertEquals(StatusEnum.NOT_PROCESSED, resultEnum);
		assertEquals(orderDto.getTotal(), response.getBody().getTotal());
		
	}
	
	@Test
	void updateOrderTest() {
		
		OrderDto orderUpdate = cloneOrder(orderDtoReference);
		
		orderUpdate.setName("update");
		orderUpdate.setDescription("description update");
		orderUpdate.setTotal(100d);
		orderUpdate.setStatus(StatusEnum.PROCESSED);
		
		BDDMockito.given(orderRepository.findById(any())).willReturn(Optional.of(new Order()));
		BDDMockito.given(orderRepository.save(any())).willReturn(buildOrderReturn(orderUpdate));
					
		ResponseEntity<OrderDto> response = orderController.update(orderDto, 1l);
					
		assertEquals(orderUpdate.getName(), response.getBody().getName());
		assertEquals(orderUpdate.getDescription(), response.getBody().getDescription());
		assertEquals(orderUpdate.getStatus(), response.getBody().getStatus());
		assertEquals(orderUpdate.getTotal(), response.getBody().getTotal());
		
	}
	
	@Test
	void findOrderByIdTest() {
				
		BDDMockito.given(orderRepository.findById(any())).willReturn(Optional.of(buildOrderReturn(orderDtoReference)));
					
		ResponseEntity<OrderDto> response = orderController.findById(1l);
					
		assertEquals(orderDtoReference.getName(), response.getBody().getName());
		assertEquals(orderDtoReference.getDescription(), response.getBody().getDescription());
		assertEquals(orderDtoReference.getStatus(), response.getBody().getStatus());
		assertEquals(orderDtoReference.getTotal(), response.getBody().getTotal());
		
	}
	
	
	@Test
	void notFoundOrderTest() {
					
		assertThrows(BusinessException.class, () -> {
			 orderController.findById(999l);
		});		
	
	}
	@Test
	void findAllOrderTest() {
				
		BDDMockito.given(orderRepository.findAll()).willReturn(buildListOrder());
					
		ResponseEntity<List<OrderDto>> response = orderController.findAll();
					
		assertEquals(3, response.getBody().size());		
	}
	
	
	@Test
	void searchOrderTest() {
				
		BDDMockito.given(orderRepository.search(any(), any(), any(), any())).willReturn(buildListOrder());
					
		ResponseEntity<List<OrderDto>> response = orderController.search("1", "1", StatusEnum.PROCESSED, "");
					
		assertEquals(3, response.getBody().size());		
	}
	
	@Test
	void searchNotFoutOrderTest() {
				
		assertThrows(BusinessException.class, () -> {
			orderController.search("1", "1", StatusEnum.PROCESSED, "");
		});		
					
	}
	
	@Test
	void deleteOrderByIdTest() {
				
		BDDMockito.given(orderRepository.findById(any())).willReturn(Optional.of(buildOrderReturn(orderDtoReference)));
					
		ResponseEntity<OrderDto> response = orderController.delete(1l);
					
		assertEquals(orderDtoReference.getName(), response.getBody().getName());
		assertEquals(orderDtoReference.getDescription(), response.getBody().getDescription());
		assertEquals(orderDtoReference.getStatus(), response.getBody().getStatus());
		assertEquals(orderDtoReference.getTotal(), response.getBody().getTotal());
		
	}
	
	Order buildOrderReturn(OrderDto orderDto) {
				
		Order order =  new ModelMapper().map(orderDto, Order.class);
		
		order.setId(1l);
		
		return order;
	}
	
	List<Order> buildListOrder(){
		List<Order> orders = new ArrayList<>();
		
		orders.add(buildOrderReturn(orderDtoReference));
		orders.add(buildOrderReturn(orderDtoReference));
		orders.add(buildOrderReturn(orderDtoReference));
		
		return orders;
	}
	
	OrderDto cloneOrder(OrderDto orderDto) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(orderDto), OrderDto.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return orderDto;
		}
	}
}
