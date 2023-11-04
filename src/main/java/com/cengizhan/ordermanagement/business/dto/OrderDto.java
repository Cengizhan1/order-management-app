package com.cengizhan.ordermanagement.business.dto;

import com.cengizhan.ordermanagement.auditing.AuditingAwareBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation

// CategoryDto(1) - BlogDto(N)
public class OrderDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;

    private Double totalPrice;
    private Long customerId;
}