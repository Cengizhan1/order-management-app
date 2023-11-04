package com.cengizhan.ordermanagement.data.repository;

import com.cengizhan.ordermanagement.data.entity.CustomerEntity;
import com.cengizhan.ordermanagement.data.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity,Long> {
    List<CustomerEntity> findByNameContainingIgnoreCase(String name);

    List<CustomerEntity> findByRelationOrderEntityListIsNull();

}