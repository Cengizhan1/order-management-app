package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.entity.Customer;
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


    // CREATE
//    @Transactional // create, delete, update
//    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
//        if(customerDto!=null){
//            Customer customer =dtoToEntity(customerDto);
//            iCustomerRepository.save(customer);
//            customerDto.setId(customer.getCustomerId());
//            customerDto.setCreatedDate(customer.getCreatedDate());
//        }else{
//            throw  new NullPointerException( " CustomerDto null");
//        }
//        return customerDto;
//    }

//    // LIST
//    public List<CustomerDto> customerServiceList() {
//        Iterable<Customer> entityIterable=  iCustomerRepository.findAll();
//        List<CustomerDto> customerDtoList=new ArrayList<>();
//        for (Customer entity:  entityIterable) {
//            CustomerDto customerDto=entityToDto(entity);
//            customerDto.setCreatedDate(entity.getCreatedDate());
//            customerDtoList.add(customerDto);
//        }
//        return customerDtoList;
//    }
//
//    // FIND
//    public CustomerDto customerServiceFindById(Long id) {
//        Customer findCustomer =  null;
//        if(id!=null){
//            findCustomer =  iCustomerRepository.findById(id)
//                    .orElseThrow(()->new ResourceNotFoundException(id+" nolu id yoktur"));
//
//        }else {
//            throw new CustomException("İd null olarak geldi");
//        }
//        return entityToDto(findCustomer);
//    }
//
//    // UPDATE
//    @Transactional // create, delete, update
//    public CustomerDto customerServiceUpdate(Long id, CustomerDto customerDto) {
//        CustomerDto customerFindDto= customerServiceFindById(id);
//        if(customerFindDto!=null){
//            Customer customer =dtoToEntity(customerFindDto);
//            customer.setName(customerDto.getName());
//            customer.setAge(customerDto.getAge());
//            iCustomerRepository.save(customer);
//            customerDto.setId(customer.getCustomerId());
//            customerDto.setCreatedDate(customer.getCreatedDate());
//
//        }
//        return customerDto;
//    }
//
//    // DELETE
//    @Transactional // create, delete, update
//    public CustomerDto customerServiceDeleteById(Long id) {
//        CustomerDto customerFindDto=customerServiceFindById(id);
//        if(customerFindDto!=null){
//            iCustomerRepository.deleteById(id);
//            customerFindDto.setId(id);
//        }
//        return customerFindDto;
//    }
//
//
//    @Transactional // create, delete, update
//    public CustomerDto customerServiceDeleteAll() {
//            iCustomerRepository.deleteAll();
//        return null;
//    }
//
//
//    public List<CustomerDto> getCustomersByNameContains(String keyword) {
//        List<Customer> customerEntities = iCustomerRepository.findByNameContainingIgnoreCase(keyword);
//        List<CustomerDto> customerDtoList = new ArrayList<>();
//
//        for (Customer entity : customerEntities) {
//            CustomerDto customerDto = entityToDto(entity);
//            customerDtoList.add(customerDto);
//        }
//        return customerDtoList;
//    }
//
//
//    public List<CustomerDto> getCustomersWithoutOrders() {
//        List<Customer> customerEntities = iCustomerRepository.findByRelationOrderListIsNull();
//        List<CustomerDto> customerDtoList = new ArrayList<>();
//        for (Customer entity : customerEntities) {
//            CustomerDto customerDto = entityToDto(entity);
//            customerDtoList.add(customerDto);
//        }
//        return customerDtoList;
//    }


}