package com.cengizhan.ordermanagement.data.repository;

import com.cengizhan.ordermanagement.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity,Long> {

}