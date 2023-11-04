package com.cengizhan.ordermanagement.controller.impl;

import com.cengizhan.ordermanagement.business.dto.CustomerDto;
import com.cengizhan.ordermanagement.business.services.ICustomerServices;
import com.cengizhan.ordermanagement.controller.ICustomerApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/customer/api/v1")
public class CustomerApiImpl implements ICustomerApi<CustomerDto> {

    // Injection
    private final ICustomerServices iCustomerServices;

    // CREATE
    // http://localhost:8000/ customer/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> customerApiCreate(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(iCustomerServices.customerServiceCreate(customerDto));
    }

    // LIST
    // http://localhost:8000/ customer/api/v1/list
    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<List<CustomerDto>> customerApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerServices.customerServiceList());
    }

    // FIND
    // http://localhost:8000/ customer/api/v1/find/1
    @Override
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?>  customerApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iCustomerServices.customerServiceFindById(id));
    }

    // UPDATE
    // http://localhost:8000/ customer/api/v1/update/1
    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> customerApiUpdate(@PathVariable(name = "id") Long id, @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(iCustomerServices.customerServiceUpdate(id, customerDto));
    }

    // DELETE BY ID
    // http://localhost:8000/ customer/api/v1/delete/1
    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> customerApiDeleteById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(iCustomerServices.customerServiceDeleteById(id), HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////
    // ALL DELETE
    // http://localhost:8000/ customer/api/v1/all/delete
    @Override
    @PostMapping(value = "/all/delete")
    public ResponseEntity<?> customerApiAllDelete() {
        return new ResponseEntity<>(iCustomerServices.customerServiceDeleteAll(), HttpStatus.OK);
    }
}