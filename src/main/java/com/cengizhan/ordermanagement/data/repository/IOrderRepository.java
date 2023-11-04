package com.cengizhan.ordermanagement.data.repository;

import com.cengizhan.ordermanagement.data.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity,Long> {
    List<OrderEntity> findByRelationCustomerEntityCustomerId(Long customerId);

}