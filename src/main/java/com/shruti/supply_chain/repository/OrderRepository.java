package com.shruti.supply_chain.repository;

import com.shruti.supply_chain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findBySupplierId(Long supplierId);
}