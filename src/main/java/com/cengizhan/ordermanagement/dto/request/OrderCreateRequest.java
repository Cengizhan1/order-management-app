package com.cengizhan.ordermanagement.dto.request;

import java.time.LocalDateTime;

public record OrderCreateRequest(
        Double totalPrice,
        Long customerId
) {}
