package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.dtos.SaleValidationResponse;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.Payment;

import java.util.Date;
import java.util.List;

public interface SaleService {
    SaleResponse saveSale(SaleRequest saleRequest);
    SaleResponse updateSale(Long saleId);
    void deleteSale(Long saleId);
    List<SaleResponse> getAllSales();
    SaleValidationResponse saleValidation(List<PaymentRequest> paymentRequests, Long saleId);
    SaleResponse getSaleById(Long saleId);
}
