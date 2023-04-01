package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.SaleDetail;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDetailResponse {
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private Long productId;
    private String designation;



}
