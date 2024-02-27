package com.cengizhan.ordermanagement.dto.request;


import com.cengizhan.ordermanagement.entity.Customer;

public record CustomerCreateRequest(
        String name,
        Integer age
) {}