package com.cengizhan.ordermanagement;

import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.entity.Order;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class TestSupport {

    public static final String CUSTOMER_API_ENDPOINT = "/v1/api/customer";
    public static final String ORDER_API_ENDPOINT = "/v1/api/order";

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Customer generateCustomer() {
        return Customer.builder()
                .name("Cengizhan")
                .age(30)
                .build();
    }

    public Order generateOrder(Customer customer) {
        return Order.builder()
                .totalPrice(100.0)
                .createdAt(getLocalDateTime())
                .relationCustomer(customer)
                .build();
    }
}
