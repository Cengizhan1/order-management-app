package com.cengizhan.ordermanagement.repository;

import com.cengizhan.ordermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByNameContainingIgnoreCase(String name);

    List<Customer> findAllByRelationOrderListEmpty();

}