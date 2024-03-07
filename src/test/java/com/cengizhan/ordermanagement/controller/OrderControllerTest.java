package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.IntegrationTestSupport;
import com.cengizhan.ordermanagement.dto.OrderDto;
import com.cengizhan.ordermanagement.dto.request.CustomerUpdateRequest;
import com.cengizhan.ordermanagement.dto.request.OrderCreateRequest;
import com.cengizhan.ordermanagement.dto.request.OrderUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest extends IntegrationTestSupport {


    @Test
    void testOrderCreate_whenCustomerExists_shouldReturnOrderDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        OrderCreateRequest request = new OrderCreateRequest(
                100.0,
                customer.getId()
        );
        Order order = orderRepository.save(generateOrder(customer));
        OrderDto expected = OrderDto.convert(order);

        this.mockMvc.perform(post(ORDER_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPrice", is(expected.totalPrice())))
                .andExpect(jsonPath("$.customerDto.name", is(customer.getName())))
                .andExpect(jsonPath("$.customerDto.age", is(customer.getAge())))
                .andReturn();
    }

    @Test
    void testOrderCreate_whenCustomerDoesNotExists_shouldReturnOrderDto() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest(
                100.0,
                15115L
        );
        this.mockMvc.perform(post(ORDER_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testOrderList_shouldReturnOrderDtoList() throws Exception {
        List<OrderDto> expected = orderRepository.findAll().stream().map(OrderDto::convert).toList();

        this.mockMvc.perform(get(ORDER_API_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter()
                        .writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    void testOrderFindById_whenIdExists_shouldReturnOrderDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        Order order = orderRepository.save(generateOrder(customer));

        this.mockMvc.perform(get(ORDER_API_ENDPOINT + "/" + order.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPrice", is(order.getTotalPrice())))
                .andExpect(jsonPath("$.customerDto.name", is(customer.getName())))
                .andExpect(jsonPath("$.customerDto.age", is(customer.getAge())))
                .andReturn();
    }
    @Test
    void testOrderFindById_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(get(ORDER_API_ENDPOINT + "/" + 514151L))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testOrderUpdate_whenIdExists_shouldReturnOrderDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        Order order = orderRepository.save(generateOrder(customer));
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest(
                100.0
        );

        this.mockMvc.perform(put(ORDER_API_ENDPOINT + "/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(orderUpdateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPrice", is(orderUpdateRequest.totalPrice())))
                .andExpect(jsonPath("$.customerDto.id", is(notNullValue())))
                .andExpect(jsonPath("$.customerDto.name", is(customer.getName())))
                .andExpect(jsonPath("$.customerDto.age", is(customer.getAge())))
                .andReturn();
    }

    @Test
    void testOrderUpdate_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(put(ORDER_API_ENDPOINT + "/" + 514151L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CustomerUpdateRequest("name", 20))))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testOrderDeleteById_whenIdExists_shouldReturn204NoContent() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        Order order = orderRepository.save(generateOrder(customer));

        this.mockMvc.perform(delete(ORDER_API_ENDPOINT + "/" + order.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testOrderDeleteById_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(delete(ORDER_API_ENDPOINT + "/" + 514151L))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testOrderAllDelete_shouldReturnAllDeleted() throws Exception {
        this.mockMvc.perform(post(ORDER_API_ENDPOINT + "/all/delete"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testGetOrdersAfterDate_shouldReturnOrderDtoList() throws Exception {

        List<OrderDto> expected = orderRepository.findAllByCreatedAtAfter(
                LocalDateTime.of(2022, 1, 1, 0, 0))
                .stream()
                .map(OrderDto::convert).toList();

        this.mockMvc.perform(get(ORDER_API_ENDPOINT + "/after/2024-01-27T16:04:52.477776400"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter()
                        .writeValueAsString(expected), false))
                .andReturn();
    }
}