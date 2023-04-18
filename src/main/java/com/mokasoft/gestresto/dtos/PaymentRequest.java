package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private PaymentType paymentType;
    private BigDecimal payment;
}
