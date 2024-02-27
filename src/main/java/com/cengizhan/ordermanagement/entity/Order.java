package com.cengizhan.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long orderId;

    @Column(name = "total_price")
    private Double totalPrice;

    //  RELATION
    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private Customer relationCustomer;
}