package com.cengizhan.ordermanagement.dto;


import com.cengizhan.ordermanagement.entity.Order;

import java.time.LocalDateTime;

public record OrderDto(
        Double totalPrice,
        CustomerDto customerDto,
        LocalDateTime createdAt
) {
    public static OrderDto convert(Order order) {
        return new OrderDto(
                order.getTotalPrice(),
                CustomerDto.convert(order.getRelationCustomer()),
                order.getCreatedAt());
    }
}