package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleValidationResponse {
    private List<PaymentResponse> payments;
    private BigDecimal totalPayment;
    private BigDecimal leftToPay;
}
