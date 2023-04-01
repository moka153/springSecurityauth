package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Product;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class SaleDetailRequest {
    private Long productId;
    private String designation;
    private BigDecimal costPrice;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
}
