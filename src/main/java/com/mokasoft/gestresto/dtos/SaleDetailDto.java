package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Product;
import com.mokasoft.gestresto.entities.Sale;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaleDetailDto {
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private BigDecimal discount;
    private BigDecimal benefit;
    private SaleDto saleDto;
    private ProductDto productDto;
}
