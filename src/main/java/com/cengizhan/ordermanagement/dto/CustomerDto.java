package com.cengizhan.ordermanagement.dto;

import com.cengizhan.ordermanagement.entity.Customer;

public record CustomerDto(
        String name,
        Integer age
) {
    public static CustomerDto convert(Customer customer) {
        return new CustomerDto(
                customer.getName(),
                customer.getAge());
    }
}