package com.cengizhan.ordermanagement.data.entity;

import com.cengizhan.ordermanagement.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.List;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "relationCustomerEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderEntity> relationOrderEntityList;

}
