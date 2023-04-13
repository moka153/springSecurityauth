package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDetailRequest {
    private Long productId;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private String note;
    private List<OptionRequest> options;
}
