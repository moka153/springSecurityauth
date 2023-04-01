package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.SaleType;
import lombok.Data;

import java.util.List;
@Data
public class SaleRequest {
    private SaleType type;
    private Long appUser;
    private AppTableDto appTableDto;
    private List<SaleDetailRequest> saleDetailRequestList;
}
