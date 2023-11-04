package com.cengizhan.ordermanagement.business.services;
import java.util.List;

// D: Dto
// E: Entity
public interface ICustomerServices<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D customerServiceCreate(D d);

    // LIST
    public List<D> customerServiceList();

    // FIND BY
    public D customerServiceFindById(Long id);

    // UPDATE
    public D customerServiceUpdate(Long id,D d);

    // DELETE
    public D customerServiceDeleteById(Long id);

    // All DELETE
    public D customerServiceDeleteAll();

    public List<D> getCustomersByNameContains(String keyword);

    public List<D> getCustomersWithoutOrders();

}