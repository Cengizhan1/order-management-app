package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.dto.OrderDto;
import com.cengizhan.ordermanagement.dto.request.OrderCreateRequest;
import com.cengizhan.ordermanagement.dto.request.OrderUpdateRequest;
import com.cengizhan.ordermanagement.service.OrderService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RateLimiter(name = "basic")
@RequestMapping("/v1/api/order")
@Tag(name = "Order Management APP API v1", description = "Order API")
public class OrderController {

    // Injection
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // http://localhost:8000/v1/api/order
    @PostMapping
    public ResponseEntity<OrderDto> orderCreate(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        return ResponseEntity.ok(orderService.orderCreate(orderCreateRequest));
    }


    // http://localhost:8000/v1/api/order
    @GetMapping
    public ResponseEntity<List<OrderDto>> orderList() {
        return ResponseEntity.ok(orderService.orderList());
    }


    // http://localhost:8000/v1/api/order/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto>  orderFindById(@NotBlank @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(orderService.orderFindById(id));
    }


    // http://localhost:8000/v1/api/order/1
    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDto> orderUpdate(@NotBlank @PathVariable(name = "id") Long id,
                                            @Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return ResponseEntity.ok(orderService.orderUpdate(id, orderUpdateRequest));
    }

    // http://localhost:8000/v1/api/order/1
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> orderDeleteById(@NotBlank @PathVariable(name = "id") Long id) {
        orderService.orderDeleteById(id);
        return ResponseEntity.ok().build();
    }


    // http://localhost:8000/v1/api/order/all/delete
    @PostMapping(value = "/all/delete")
    public ResponseEntity<Void> orderAllDelete() {
        orderService.orderDeleteAll();
        return ResponseEntity.ok().build();
    }

    // http://localhost:8000/v1/api/order/after/{date}
    @GetMapping("/after/{date}")
    public ResponseEntity<List<OrderDto>> getOrdersAfterDate(
            @PathVariable("date") LocalDateTime date) {
        return ResponseEntity.ok(orderService.orderFindAllByCreatedAtAfter(date));
    }

}