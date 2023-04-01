package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.SaleType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleResponse {
    private Long saleId;
    private Date saleDate;
    private SaleType type;
    private BigDecimal amount;
    private AppTableDto appTableDto;
    private List<SaleDetailResponse> saleDetailResponses;
}
