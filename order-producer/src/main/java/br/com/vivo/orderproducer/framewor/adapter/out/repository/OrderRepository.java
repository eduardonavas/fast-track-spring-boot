package br.com.vivo.orderproducer.framewor.adapter.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;

public interface OrderRepository  extends JpaRepository<Order, Long>{
	
	
	@Query("SELECT o FROM Order o WHERE (o.name = :q OR o.description = :q) AND o.total <= :maxTotal AND o.total >= :minTotal AND o.status = :status")
	List<Order> search(@Param("maxTotal") Double maxTotal, @Param("minTotal") Double minTotal, @Param("status") StatusEnum status, @Param("q") String q);
}
