package br.com.vivo.orderproducer.framework.adapter.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vivo.orderproducer.domain.Order;
import br.com.vivo.orderproducer.domain.StatusEnum;

public interface OrderRepository  extends JpaRepository<Order, Long>{
	
	
	@Query("SELECT o FROM Order o WHERE (:q IS NULL OR LOWER (o.name) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER (o.description) LIKE LOWER(CONCAT('%', :q, '%')))"
			+ " AND (:maxTotal IS NULL OR o.total <= :maxTotal)"
			+ " AND (:minTotal IS NULL OR o.total >= :minTotal)"
			+ " AND (:status IS NULL OR o.status = :status)")
	List<Order> search(@Param("maxTotal") Double maxTotal, @Param("minTotal") Double minTotal, @Param("status") StatusEnum status, @Param("q") String q);
}
