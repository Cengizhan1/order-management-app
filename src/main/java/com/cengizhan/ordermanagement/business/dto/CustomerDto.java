package com.cengizhan.ordermanagement.business.dto;

import com.cengizhan.ordermanagement.auditing.AuditingAwareBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation

// CategoryDto(1) - BlogDto(N)
public class CustomerDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;
    private String name;
    private Integer age;
}