package com.mokasoft.gestresto.dtos;


import com.mokasoft.gestresto.enums.SaleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponse {
    private Long saleId;
    private Date saleDate;
    private SaleType type;
    private BigDecimal amount;
    private AppTableResponse table;
    private List<SaleDetailResponse> saleDetailResponses;
}
