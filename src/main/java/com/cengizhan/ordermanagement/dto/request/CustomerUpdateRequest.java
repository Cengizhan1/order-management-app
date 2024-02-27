package com.cengizhan.ordermanagement.dto.request;


public record CustomerUpdateRequest(
        String name,
        Integer age
) {}