package com.cengizhan.ordermanagement.repository;

import com.cengizhan.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByRelationCustomerCustomerId(Long customerId);
    List<Order> findByCreatedAtAfter(LocalDateTime date);


}