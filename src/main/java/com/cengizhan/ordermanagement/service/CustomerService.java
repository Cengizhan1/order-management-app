package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.dto.request.CustomerCreateRequest;
import com.cengizhan.ordermanagement.dto.request.CustomerUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.exception.CustomerNotFoundException;
import com.cengizhan.ordermanagement.repository.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final ICustomerRepository iCustomerRepository;

    public CustomerService(ICustomerRepository iCustomerRepository) {
        this.iCustomerRepository = iCustomerRepository;
    }

    protected Customer getCustomer(Long id) {
        return iCustomerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id));

    }


    @Transactional
    public CustomerDto customerCreate(CustomerCreateRequest customerCreateRequest) {
        Customer customer = Customer.builder()
                .name(customerCreateRequest.name())
                .age(customerCreateRequest.age())
                .build();
        return CustomerDto.convert(iCustomerRepository.save(customer));
    }


    public List<CustomerDto> customerList() {
        return iCustomerRepository.findAll()
                .stream()
                .map(CustomerDto::convert)
                .toList();
    }

    public CustomerDto customerFindById(Long id) {
        return CustomerDto.convert(getCustomer(id));
    }


    @Transactional
    public CustomerDto customerUpdate(Long id, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = getCustomer(id);
        customer.setName(customerUpdateRequest.name());
        customer.setAge(customerUpdateRequest.age());
        return CustomerDto.convert(iCustomerRepository.save(customer));
    }

    @Transactional // create, delete, update
    public void customerDeleteById(Long id) {
        getCustomer(id);// TODO deleteById metodu mevcut olmayan bir id geldiğinde nasıl hata verir handle edilebilir mi
        iCustomerRepository.deleteById(id);
    }

    @Transactional
    public void customerDeleteAll() {
        iCustomerRepository.deleteAll();
    }

    public List<CustomerDto> getCustomersByNameContaining(String keyword) {
        return iCustomerRepository.findAllByNameContainingIgnoreCase(keyword)
                .stream()
                .map(CustomerDto::convert)
                .toList();
    }

    public List<CustomerDto> getCustomersWithoutOrders() {
        return iCustomerRepository.findAllByRelationOrderListEmpty()
                .stream()
                .map(CustomerDto::convert)
                .toList();
    }
}