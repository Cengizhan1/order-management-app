package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.dto.OrderDto;
import com.cengizhan.ordermanagement.entity.Order;
import com.cengizhan.ordermanagement.repository.ICustomerRepository;
import com.cengizhan.ordermanagement.repository.IOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final IOrderRepository iOrderRepository;
    private final ICustomerRepository iCustomerRepository;

    public OrderService(IOrderRepository iOrderRepository, ICustomerRepository iCustomerRepository) {
        this.iOrderRepository = iOrderRepository;
        this.iCustomerRepository = iCustomerRepository;
    }


    // CREATE
//    @Transactional // create, delete, update
//    public OrderDto orderServiceCreate(OrderDto orderDto) {
//        if (orderDto != null) {
//            Order order = dtoToEntity(orderDto);
//            order.setRelationCustomer(iCustomerRepository.findById(orderDto.getCustomerId()).get());
//            iOrderRepository.save(order);
//            orderDto.setId(order.getOrderId());
//        } else {
//            throw new NullPointerException(" OrderDto null");
//        }
//        return orderDto;
//    }
//
//    // LIST
//    public List<OrderDto> orderServiceList() {
//        Iterable<Order> entityIterable=  iOrderRepository.findAll();
//        List<OrderDto> orderDtoList = new ArrayList<>();
//        for (Order entity : entityIterable) {
//            OrderDto orderDto = entityToDto(entity);
//            orderDtoList.add(orderDto);
//        }
//        log.info("Liste Sayısı: " + orderDtoList.size());
//        return orderDtoList;
//    }
//
//
//    // FIND
//    public OrderDto orderServiceFindById(Long id) {
//        Order findOrder = null;
//        if (id != null) {
//            findOrder = iOrderRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu id yoktur"));
//        } else {
//            throw new CustomException("İd null olarak geldi");
//        }
//        return entityToDto(findOrder);
//    }
//
//    // UPDATE
//    @Transactional // create, delete, update
//    public OrderDto orderServiceUpdate(Long id, OrderDto orderDto) {
//        OrderDto orderFindDto = orderServiceFindById(id);
//        if (orderFindDto != null) {
//            Order order = dtoToEntity(orderFindDto);
//            order.setTotalPrice(orderDto.getTotalPrice());
//            order.setRelationCustomer(iCustomerRepository.findById(orderDto.getCustomerId()).get());
//            iOrderRepository.save(order);
//            orderDto.setId(id);
//        }
//        return orderDto;
//    }
//
//    // DELETE
//    @Transactional // create, delete, update
//    public OrderDto orderServiceDeleteById(Long id) {
//        OrderDto orderFindDto = orderServiceFindById(id);
//        if (orderFindDto != null) {
//            iOrderRepository.deleteById(id);
//        }
//        return orderFindDto;
//    }
//
//
//    @Transactional // create, delete, update
//    public OrderDto orderServiceDeleteAll() {
//        iOrderRepository.deleteAll();
//        return null;
//    }
//
//
//    public List<OrderDto> orderServiceFindByCreatedDateAfter(Date date) {
//        List<Order> orderEntities = iOrderRepository.findByCreatedDateAfter(date);
//        log.info(date);
//        log.info(orderEntities.size());
//        List<OrderDto> orderDtoList = new ArrayList<>();
//        for (Order entity : orderEntities) {
//            OrderDto orderDto = entityToDto(entity);
//            orderDtoList.add(orderDto);
//        }
//
//        return orderDtoList;
//    }



}