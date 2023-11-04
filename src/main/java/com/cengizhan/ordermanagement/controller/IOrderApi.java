package com.cengizhan.ordermanagement.controller;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderApi <D> {

    // C R U D
    // CREATE
    public ResponseEntity<?>  orderApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>>  orderApiList();

    // FIND BY
    public ResponseEntity<?>  orderApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?>  orderApiUpdate(Long id,D d);

    // DELETE
    public ResponseEntity<?>  orderApiDeleteById(Long id);

    //////////////////////////////////////
    // ALL DELETE
    public ResponseEntity<?> orderApiAllDelete();
}