package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.SaleStatus;
import com.mokasoft.gestresto.enums.SaleType;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class SaleRequest {
    private SaleType type;
    private Date saleDate = new Date();
    private Long userId;
    private Long tableId;
    private int customerNumber;
    //private SaleStatus saleStatus;
    private List<SaleDetailRequest> saleDetail;
}
