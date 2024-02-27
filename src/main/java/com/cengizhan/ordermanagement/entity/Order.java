package com.cengizhan.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    private LocalDateTime createdAt;
    private Double totalPrice;

    //  RELATION
    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private Customer relationCustomer;
}