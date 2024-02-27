package com.cengizhan.ordermanagement.dto.request;

import java.time.LocalDateTime;

public record OrderUpdateRequest(
        Double totalPrice
) {
}
