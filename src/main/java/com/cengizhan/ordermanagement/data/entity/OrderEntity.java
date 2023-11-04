package com.cengizhan.ordermanagement.data.entity;

import com.cengizhan.ordermanagement.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;

// LOMBOK
@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long orderId;

    @Column(name = "total_price")
    private Double totalPrice;

    //  RELATION
    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private CustomerEntity relationCustomerEntity;
}