package com.cengizhan.ordermanagement.business.services.impl;

import com.cengizhan.ordermanagement.bean.ModelMapperBean;
import com.cengizhan.ordermanagement.business.dto.OrderDto;
import com.cengizhan.ordermanagement.business.services.IOrderServices;
import com.cengizhan.ordermanagement.data.entity.OrderEntity;
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

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICES
@Service
public class OrderServicesImpl implements IOrderServices<OrderDto, OrderEntity> {

    private final IOrderRepository iOrderRepository;
    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;


    // MODEL MAPPER
    @Override
    public OrderDto entityToDto(OrderEntity orderEntity) {
        return modelMapperBean.modelMapperMethod().map(orderEntity, OrderDto.class);
    }

    @Override
    public OrderEntity dtoToEntity(OrderDto orderDto) {
        return modelMapperBean.modelMapperMethod().map(orderDto, OrderEntity.class);
    }

    // CREATE
    @Override
    @Transactional // create, delete, update
    public OrderDto orderServiceCreate(OrderDto orderDto) {
        if (orderDto != null) {
            OrderEntity orderEntity = dtoToEntity(orderDto);
            orderEntity.setRelationCustomerEntity(iCustomerRepository.findById(orderDto.getCustomerId()).get());
            iOrderRepository.save(orderEntity);
            orderDto.setId(orderEntity.getOrderId());
        } else {
            throw new NullPointerException(" OrderDto null");
        }
        return orderDto;
    }

    // LIST
    @Override
    public List<OrderDto> orderServiceList() {
        Iterable<OrderEntity> entityIterable=  iOrderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity entity : entityIterable) {
            OrderDto orderDto = entityToDto(entity);
            orderDtoList.add(orderDto);
        }
        log.info("Liste Sayısı: " + orderDtoList.size());
        return orderDtoList;
    }


    // FIND
    @Override
    public OrderDto orderServiceFindById(Long id) {
        OrderEntity findOrderEntity = null;
        if (id != null) {
            findOrderEntity = iOrderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu id yoktur"));
        } else {
            throw new CustomException("İd null olarak geldi");
        }
        return entityToDto(findOrderEntity);
    }

    // UPDATE
    @Override
    @Transactional // create, delete, update
    public OrderDto orderServiceUpdate(Long id, OrderDto orderDto) {
        OrderDto orderFindDto = orderServiceFindById(id);
        if (orderFindDto != null) {
            OrderEntity orderEntity = dtoToEntity(orderFindDto);
            orderEntity.setTotalPrice(orderDto.getTotalPrice());
            orderEntity.setRelationCustomerEntity(iCustomerRepository.findById(orderDto.getCustomerId()).get());
            iOrderRepository.save(orderEntity);
            orderDto.setId(id);
        }
        return orderDto;
    }

    // DELETE
    @Override
    @Transactional // create, delete, update
    public OrderDto orderServiceDeleteById(Long id) {
        OrderDto orderFindDto = orderServiceFindById(id);
        if (orderFindDto != null) {
            iOrderRepository.deleteById(id);
        }
        return orderFindDto;
    }

    @Override
    @Transactional // create, delete, update
    public OrderDto orderServiceDeleteAll() {
        iOrderRepository.deleteAll();
        return null;
    }


}