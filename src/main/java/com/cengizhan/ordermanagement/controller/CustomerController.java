package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer/api/v1")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //cREATE
    // http://localhost:8000/customer/api/v1/create
//    @PostMapping("/create")
//    public ResponseEntity<?> customerApiCreate(@Valid @RequestBody CustomerDto customerDto) {
//        return ResponseEntity.ok(customerService.customerServiceCreate(customerDto));
//    }
//
//    // LIST
//    // http://localhost:8000/customer/api/v1/list
//    @GetMapping(value = "/list")
//    public ResponseEntity<List<CustomerDto>> customerApiList() {
//        return ResponseEntity.status(HttpStatus.OK).body(customerService.customerServiceList());
//    }
//
//    // FIND
//    // http://localhost:8000/customer/api/v1/find/1
//    @GetMapping(value = "/find/{id}")
//    public ResponseEntity<?>  customerApiFindById(@PathVariable(name = "id") Long id) {
//        return ResponseEntity.status(200).body(customerService.customerServiceFindById(id));
//    }
//
//    // UPDATE
//    // http://localhost:8000/customer/api/v1/update/1
//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<?> customerApiUpdate(@PathVariable(name = "id") Long id, @Valid @RequestBody CustomerDto customerDto) {
//        return ResponseEntity.ok().body(customerService.customerServiceUpdate(id, customerDto));
//    }
//
//    // DELETE BY ID
//    // http://localhost:8000/customer/api/v1/delete/1
//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<?> customerApiDeleteById(@PathVariable(name = "id") Long id) {
//        return new ResponseEntity<>(customerService.customerServiceDeleteById(id), HttpStatus.OK);
//    }
//
//    // ALL DELETE
//    // http://localhost:8000/customer/api/v1/all/delete
//    @PostMapping(value = "/all/delete")
//    public ResponseEntity<?> customerApiAllDelete() {
//        return new ResponseEntity<>(customerService.customerServiceDeleteAll(), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/getByNameContains")
//    public ResponseEntity<List<CustomerDto>> getCustomersByNameContains(@RequestParam String keyword) {
//        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersByNameContains(keyword));
//    }
//
//
//    @GetMapping("/getCustomersWithoutOrders")
//    public ResponseEntity<List<CustomerDto>> getCustomersWithoutOrders() {
//        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersWithoutOrders());
//    }
}