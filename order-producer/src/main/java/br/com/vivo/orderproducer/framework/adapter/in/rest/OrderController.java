package br.com.vivo.orderproducer.framework.adapter.in.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.orderproducer.application.port.in.OrderPortIn;
import br.com.vivo.orderproducer.domain.StatusEnum;
import br.com.vivo.orderproducer.domain.dto.OrderDto;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderPortIn orderPortIn;
			
	@GetMapping
	public ResponseEntity<List<OrderDto>> findAll(){
		return ResponseEntity.ok(orderPortIn.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
				
		return ResponseEntity.ok(orderPortIn.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<OrderDto>> search(@RequestParam(name = "max_total", required = false) String maxTotal, 
			@RequestParam(name = "min_total", required = false) String minTotal,
			@RequestParam(required = false) StatusEnum status, @RequestParam(required = false) String q){
		
		return ResponseEntity.ok(orderPortIn.search(maxTotal, minTotal, status, q));
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderDto orderDto){
		return new ResponseEntity<>(orderPortIn.save(orderDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto, @PathVariable Long id) {
		return ResponseEntity.ok(orderPortIn.update(orderDto, id));
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<OrderDto> delete(@PathVariable Long id) {
		return ResponseEntity.ok(orderPortIn.delete(id));
	}
}
