package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;

import java.util.List;

public interface SaleDetailService {
    List<SaleDetailResponse> getSaleDetails(Sale sale);
}
