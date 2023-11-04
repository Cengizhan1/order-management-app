package com.cengizhan.ordermanagement.controller.impl;

import com.cengizhan.ordermanagement.business.dto.OrderDto;
import com.cengizhan.ordermanagement.business.services.IOrderServices;
import com.cengizhan.ordermanagement.controller.IOrderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
// LOMBOK
@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/order/api/v1")
public class OrderApiImpl implements IOrderApi<OrderDto> {

    // Injection
    private final IOrderServices iOrderServices;

    // CREATE
    // http://localhost:8000/ order/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> orderApiCreate(@Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(iOrderServices.orderServiceCreate(orderDto));
    }

    // LIST
    // http://localhost:8000/ order/api/v1/list
    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderDto>> orderApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iOrderServices.orderServiceList());
    }

    // FIND
    // http://localhost:8000/ order/api/v1/find/1
    @Override
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?>  orderApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iOrderServices.orderServiceFindById(id));
    }

    // UPDATE
    // http://localhost:8000/ order/api/v1/update/1
    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> orderApiUpdate(@PathVariable(name = "id") Long id, @Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().body(iOrderServices.orderServiceUpdate(id, orderDto));
    }

    // DELETE BY ID
    // http://localhost:8000/ order/api/v1/delete/1
    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> orderApiDeleteById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(iOrderServices.orderServiceDeleteById(id), HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////
    // ALL DELETE
    // http://localhost:8000/ order/api/v1/all/delete
    @Override
    @PostMapping(value = "/all/delete")
    public ResponseEntity<?> orderApiAllDelete() {
        return new ResponseEntity<>(iOrderServices.orderServiceDeleteAll(), HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////
    // ALL DELETE
    // http://localhost:8000/ order/api/v1/after/{date}
    @Override
    @GetMapping("/after")
    public ResponseEntity<List<OrderDto>> getOrdersAfterDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.status(HttpStatus.OK).body(iOrderServices.orderServiceFindByCreatedDateAfter(date));
    }

}