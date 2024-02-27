package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.dto.request.CustomerCreateRequest;
import com.cengizhan.ordermanagement.dto.request.CustomerUpdateRequest;
import com.cengizhan.ordermanagement.service.CustomerService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RateLimiter(name = "basic")
@RequestMapping("/v1/api/customer")
@Tag(name = "Order Management APP API v1", description = "Customer API")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    // http://localhost:8000/v1/api/customer
    @PostMapping
    public ResponseEntity<CustomerDto> customerCreate(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.ok(customerService.customerCreate(customerCreateRequest));
    }

    // http://localhost:8000/v1/api/customer
    @GetMapping
    public ResponseEntity<List<CustomerDto>> customerList() {
        return ResponseEntity.ok(customerService.customerList());
    }

    // http://localhost:8000/v1/api/customer/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto>  customerFindById(@NotBlank @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(customerService.customerFindById(id));
    }

    // http://localhost:8000/v1/api/customer/1
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> customerUpdate(@NotBlank @PathVariable(name = "id") Long id,
                                            @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return ResponseEntity.ok(customerService.customerUpdate(id, customerUpdateRequest));
    }


    // http://localhost:8000/v1/api/customer/1
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> customerDeleteById(@NotBlank @PathVariable(name = "id") Long id) {
        customerService.customerDeleteById(id);
        return ResponseEntity.ok().build();
    }

    // http://localhost:8000/v1/api/customer/all/delete
    @PostMapping(value = "/all/delete")
    public ResponseEntity<Void> customerAllDelete() {
        customerService.customerDeleteAll();
        return ResponseEntity.ok().build();
    }

    // http://localhost:8000/v1/api/customer/getByNameContains/{keyword}
    @GetMapping("/getByNameContains/{keyword}")
    public ResponseEntity<List<CustomerDto>> getCustomersByNameContains(
            @PathVariable(name = "keyword") String keyword) {
        return ResponseEntity.ok(customerService.getCustomersByNameContaining(keyword));
    }

    // http://localhost:8000/v1/api/customer/getCustomersWithoutOrders
    @GetMapping("/getCustomersWithoutOrders")
    public ResponseEntity<List<CustomerDto>> getCustomersWithoutOrders() {
        return ResponseEntity.ok(customerService.getCustomersWithoutOrders());
    }
}