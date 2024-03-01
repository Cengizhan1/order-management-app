package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.dto.request.OrderCreateRequest;
import com.cengizhan.ordermanagement.dto.OrderDto;
import com.cengizhan.ordermanagement.dto.request.OrderUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.entity.Order;
import com.cengizhan.ordermanagement.exception.OrderNotFoundException;
import com.cengizhan.ordermanagement.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderService {

    private final IOrderRepository iOrderRepository;
    private final CustomerService customerService;
    private final Clock clock;
    private final Logger logger = Logger.getLogger(OrderService.class.getName());

    public OrderService(IOrderRepository iOrderRepository, CustomerService customerService, Clock clock) {
        this.iOrderRepository = iOrderRepository;
        this.customerService = customerService;
        this.clock = clock;
    }

    protected Order getOrder(Long id) {
        return iOrderRepository.findById(id)
                .orElseThrow(
                        () -> new OrderNotFoundException("Order could not found by id: " + id)
                );
    }

    @Transactional
    public OrderDto orderCreate(OrderCreateRequest orderCreateRequest) {
        Customer customer = customerService.getCustomer(orderCreateRequest.customerId());
        Order order = Order.builder()
                .totalPrice(orderCreateRequest.totalPrice())
                .relationCustomer(customer)
                .createdAt(getLocalDateTimeNow())
                .build();
        logger.info("Order created by id: " + order.getId());
        return OrderDto.convert(iOrderRepository.save(order);

    }

    public List<OrderDto> orderList() {
        return iOrderRepository.findAll()
                .stream()
                .map(OrderDto::convert)
                .toList();
    }

    public OrderDto orderFindById(Long id) {
        return OrderDto.convert(getOrder(id));
    }

    @Transactional
    public OrderDto orderUpdate(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order order = getOrder(id);
        order.setTotalPrice(orderUpdateRequest.totalPrice());
        order.setCreatedAt(getLocalDateTimeNow());
        logger.info("Order updated by id: " + order.getId());
        return OrderDto.convert(iOrderRepository.save(order));
    }

    @Transactional // create, delete, update
    public void orderDeleteById(Long id) {
        getOrder(id); // TODO deleteById metodu mevcut olmayan bir id geldiğinde nasıl hata verir handle edilebilir mi
        iOrderRepository.deleteById(id);
        logger.info("Order deleted by id: " + id);
    }

    @Transactional // create, delete, update
    public void orderDeleteAll() {
        iOrderRepository.deleteAll();
    }

    public List<OrderDto> orderFindAllByCreatedAtAfter(LocalDateTime date) {
        List<Order> orders = iOrderRepository.findAllByCreatedAtAfter(date);
        return orders.stream().map(OrderDto::convert).toList();
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}