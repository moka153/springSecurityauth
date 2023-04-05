package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;

import java.util.List;

public interface SaleDetailService {
    SaleDetail saveSaleDetail(SaleDetail saleDetail);

    List<SaleDetailResponse> getSaleDetail(Sale sale);



}
