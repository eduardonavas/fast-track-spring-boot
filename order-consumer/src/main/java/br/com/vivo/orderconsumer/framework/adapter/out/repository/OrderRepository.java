package br.com.vivo.orderconsumer.framework.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vivo.orderconsumer.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
