package com.cengizhan.ordermanagement.business.services;
import com.cengizhan.ordermanagement.business.dto.OrderDto;

import java.util.Date;
import java.util.List;

// D: Dto
// E: Entity
public interface IOrderServices<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D orderServiceCreate(D d);

    // LIST
    public List<D> orderServiceList();

    // FIND BY
    public D orderServiceFindById(Long id);

    // UPDATE
    public D orderServiceUpdate(Long id,D d);

    // DELETE
    public D orderServiceDeleteById(Long id);

    // All DELETE
    public D orderServiceDeleteAll();

    List<OrderDto> orderServiceFindByCreatedDateAfter(Date date);

}