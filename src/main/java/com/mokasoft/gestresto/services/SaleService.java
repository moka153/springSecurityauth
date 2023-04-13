package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.AppTable;

import java.util.Date;
import java.util.List;

public interface SaleService {
    SaleResponse saveSale(SaleRequest saleRequest);
    SaleResponse updateSale(SaleRequest saleRequest,Long saleId);
    void deleteSale(Long saleId);
    List<SaleResponse> getAllSales();
    void saleValidation(Long saleId);
}
