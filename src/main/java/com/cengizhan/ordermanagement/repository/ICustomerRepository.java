package com.cengizhan.ordermanagement.repository;

import com.cengizhan.ordermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);

    List<Customer> findByRelationOrderListIsNull();

}