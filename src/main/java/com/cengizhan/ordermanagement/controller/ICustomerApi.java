package com.cengizhan.ordermanagement.controller;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerApi<D> {

    // C R U D
    // CREATE
    public ResponseEntity<?>  customerApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>>  customerApiList();

    // FIND BY
    public ResponseEntity<?>  customerApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?>  customerApiUpdate(Long id,D d);

    // DELETE
    public ResponseEntity<?>  customerApiDeleteById(Long id);

    //////////////////////////////////////
    // ALL DELETE
    public ResponseEntity<?> customerApiAllDelete();


}