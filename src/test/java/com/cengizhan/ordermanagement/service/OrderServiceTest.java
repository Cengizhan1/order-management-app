package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.TestSupport;
import com.cengizhan.ordermanagement.dto.OrderDto;
import com.cengizhan.ordermanagement.dto.request.OrderCreateRequest;
import com.cengizhan.ordermanagement.dto.request.OrderUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.entity.Order;
import com.cengizhan.ordermanagement.exception.OrderNotFoundException;
import com.cengizhan.ordermanagement.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest extends TestSupport {

    private IOrderRepository iOrderRepository;
    private CustomerService customerService;
    private OrderService service;
    private Clock clock;

    @BeforeEach
    void setUp() {
        iOrderRepository = mock(IOrderRepository.class);
        customerService = mock(CustomerService.class);
        clock = mock(Clock.class);

        Clock clock = mock(Clock.class);

        service = new OrderService(iOrderRepository, customerService, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    void testOrderCreate_ShouldReturnOrderDto() {
        OrderCreateRequest createRequest = new OrderCreateRequest(100.0, 1L);
        Customer customer = Customer.builder().id(1L).name("Cengizhan").age(30).build();

        when(customerService.getCustomer(anyLong())).thenReturn(customer);

        Order order = Order.builder().totalPrice(
                        createRequest.totalPrice())
                .relationCustomer(customer)
                .createdAt(getLocalDateTime()).build();

        when(iOrderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto actualDto = service.orderCreate(createRequest);

        assertEquals(OrderDto.convert(order), actualDto);
        verify(iOrderRepository).save(any(Order.class));
    }

    @Test
    void testOrderList_ShouldReturnOrderDtoList() {
        Customer customer = (Customer.builder().name("Cengizhan").age(30).build());

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(Order.builder().totalPrice(100.0).relationCustomer(customer)
                .createdAt(getLocalDateTime()).build());
        orders.add(Order.builder().totalPrice(200.0).relationCustomer(customer)
                .createdAt(getLocalDateTime()).build());

        when(iOrderRepository.findAll()).thenReturn(orders);

        List<OrderDto> actualList = service.orderList();

        assertEquals(2, actualList.size());

        verify(iOrderRepository).findAll();
    }

    @Test
    void testOrderFindById_whenOrderExists_ShouldReturnOrderDto() {
        Order order = Order.builder().totalPrice(100.0)
                .relationCustomer(Customer.builder()
                        .name("Cengizhan").age(30).build())
                .createdAt(getLocalDateTime()).build();
        OrderDto expectedDto = OrderDto.convert(order);

        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        OrderDto actualDto = service.orderFindById(1L);

        assertEquals(expectedDto, actualDto);

        verify(iOrderRepository).findById(1L);
    }

    @Test
    void testOrderFindById_whenOrderDoesNotExists_ShouldReturnOrderNotFoundException() {
        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> service.orderFindById(1L));
    }

    @Test
    void testOrderUpdate_whenOrderExists_ShouldReturnOrderDto() {
        Order order = Order.builder().totalPrice(100.0)
                .relationCustomer(Customer.builder()
                        .name("Cengizhan").age(30).build())
                .createdAt(getLocalDateTime()).build();
        OrderDto expectedDto = OrderDto.convert(order);

        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(iOrderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto actualDto = service.orderUpdate(1L, new OrderUpdateRequest(100.0));

        assertEquals(expectedDto, actualDto);

        verify(iOrderRepository).save(any(Order.class));
    }

    @Test
    void testOrderUpdate_whenOrderDoesNotExists_ShouldReturnOrderNotFoundException() {
        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> service.orderUpdate(1L, new OrderUpdateRequest(100.0)));

        verify(iOrderRepository, never()).save(any(Order.class));
    }

    @Test
    void testOrderDeleteById_whenOrderExists_ShouldDeleteOrder() {
        Order order = Order.builder().totalPrice(100.0)
                .relationCustomer(Customer.builder()
                        .name("Cengizhan").age(30).build())
                .createdAt(getLocalDateTime()).build();

        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        service.orderDeleteById(1L);

        verify(iOrderRepository).deleteById(1L);
    }

    @Test
    void testOrderDeleteById_whenOrderDoesNotExists_ShouldDeleteOrderNotFoundException() {
        when(iOrderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> service.orderDeleteById(1L));

        verify(iOrderRepository, never()).save(any(Order.class));
    }

    @Test
    void testOrderDeleteAll_shouldDeleteAll() {
        service.orderDeleteAll();

        verify(iOrderRepository).deleteAll();
    }

    @Test
    void orderFindAllByCreatedAtAfter() {

        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder().totalPrice(100.0)
                .relationCustomer(Customer.builder()
                        .name("Cengizhan").age(30).build())
                .createdAt(getLocalDateTime()).build());
        orders.add(Order.builder().totalPrice(200.0)
                .relationCustomer(Customer.builder()
                        .name("Cengizhan").age(30).build())
                .createdAt(getLocalDateTime()).build());

        when(iOrderRepository.findAllByCreatedAtAfter(any(LocalDateTime.class))).thenReturn(orders);

        List<OrderDto> actualList = service.orderFindAllByCreatedAtAfter(getLocalDateTime());

        assertEquals(2, actualList.size());

        verify(iOrderRepository).findAllByCreatedAtAfter(any(LocalDateTime.class));
    }
}