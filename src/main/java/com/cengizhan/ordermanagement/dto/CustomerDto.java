package com.cengizhan.ordermanagement.dto;

import com.cengizhan.ordermanagement.entity.Customer;

public record CustomerDto(
        Long id,
        String name,
        Integer age
) {
    public static CustomerDto convert(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getAge());
    }
}