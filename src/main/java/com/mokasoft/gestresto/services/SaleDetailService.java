package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.Sale;

import java.util.List;

public interface SaleDetailService {
    List<SaleDetailResponse> getSaleDetails(Sale sale);

    List<SaleDetailResponse> addSaleDetail(List<SaleDetailRequest> saleDetailRequest,Long saleId);
    SaleDetailResponse updateSaleDetail(SaleDetailRequest saleDetailRequest,Long saleDetailId);
    void deleteSaleDetail(Long saleDetailId);
    SaleResponse addDetail(List<SaleDetailRequest> saleDetailRequest,Long saleId);
}
