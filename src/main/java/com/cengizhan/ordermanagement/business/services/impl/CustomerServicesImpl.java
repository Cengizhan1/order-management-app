package com.cengizhan.ordermanagement.business.services.impl;

import com.cengizhan.ordermanagement.bean.ModelMapperBean;
import com.cengizhan.ordermanagement.business.dto.CustomerDto;
import com.cengizhan.ordermanagement.business.services.ICustomerServices;
import com.cengizhan.ordermanagement.data.entity.CustomerEntity;
import com.cengizhan.ordermanagement.data.repository.ICustomerRepository;
import com.cengizhan.ordermanagement.data.repository.IOrderRepository;
import com.cengizhan.ordermanagement.exception.CustomException;
import com.cengizhan.ordermanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICES
@Service
public class CustomerServicesImpl implements ICustomerServices<CustomerDto, CustomerEntity> {

    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;
    private final IOrderRepository iOrderRepository;


    // MODEL MAPPER
    @Override
    public CustomerDto entityToDto(CustomerEntity customerEntity) {
        return modelMapperBean.modelMapperMethod().map(customerEntity,CustomerDto.class);
    }

    @Override
    public CustomerEntity dtoToEntity(CustomerDto customerDto) {
        return  modelMapperBean.modelMapperMethod().map(customerDto,CustomerEntity.class);
    }

    // CREATE
    @Override
    @Transactional // create, delete, update
    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
        if(customerDto!=null){
            CustomerEntity customerEntity=dtoToEntity(customerDto);
            iCustomerRepository.save(customerEntity);
            customerDto.setId(customerEntity.getCustomerId());
            customerDto.setCreatedDate(customerEntity.getCreatedDate());
        }else{
            throw  new NullPointerException( " CustomerDto null");
        }
        return customerDto;
    }

    // LIST
    @Override
    public List<CustomerDto> customerServiceList() {
        Iterable<CustomerEntity> entityIterable=  iCustomerRepository.findAll();
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for (CustomerEntity entity:  entityIterable) {
            CustomerDto customerDto=entityToDto(entity);
            customerDto.setCreatedDate(entity.getCreatedDate());
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    // FIND
    @Override
    public CustomerDto customerServiceFindById(Long id) {
        CustomerEntity findCustomerEntity=  null;
        if(id!=null){
            findCustomerEntity=  iCustomerRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException(id+" nolu id yoktur"));

        }else {
            throw new CustomException("İd null olarak geldi");
        }
        return entityToDto(findCustomerEntity);
    }

    // UPDATE
    @Override
    @Transactional // create, delete, update
    public CustomerDto customerServiceUpdate(Long id, CustomerDto customerDto) {
        CustomerDto customerFindDto= customerServiceFindById(id);
        if(customerFindDto!=null){
            CustomerEntity customerEntity=dtoToEntity(customerFindDto);
            customerEntity.setName(customerDto.getName());
            customerEntity.setAge(customerDto.getAge());
            iCustomerRepository.save(customerEntity);
            customerDto.setId(customerEntity.getCustomerId());
            customerDto.setCreatedDate(customerEntity.getCreatedDate());

        }
        return customerDto;
    }

    // DELETE
    @Override
    @Transactional // create, delete, update
    public CustomerDto customerServiceDeleteById(Long id) {
        CustomerDto customerFindDto=customerServiceFindById(id);
        if(customerFindDto!=null){
            iCustomerRepository.deleteById(id);
            customerFindDto.setId(id);
        }
        return customerFindDto;
    }

    @Override
    @Transactional // create, delete, update
    public CustomerDto customerServiceDeleteAll() {
            iCustomerRepository.deleteAll();
        return null;
    }

    @Override
    public List<CustomerDto> getCustomersByNameContains(String keyword) {
        List<CustomerEntity> customerEntities = iCustomerRepository.findByNameContainingIgnoreCase(keyword);
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (CustomerEntity entity : customerEntities) {
            CustomerDto customerDto = entityToDto(entity);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    @Override
    public List<CustomerDto> getCustomersWithoutOrders() {
        List<CustomerEntity> customerEntities = iCustomerRepository.findByRelationOrderEntityListIsNull();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (CustomerEntity entity : customerEntities) {
            CustomerDto customerDto = entityToDto(entity);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }


}