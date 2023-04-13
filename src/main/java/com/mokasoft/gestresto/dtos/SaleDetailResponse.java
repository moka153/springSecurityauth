package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponse {
    private Long detailId;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private Long productId;
    private String productDesignation;
    private String productPicture;
    private String note;
    private List<OptionResponse> options;



}
