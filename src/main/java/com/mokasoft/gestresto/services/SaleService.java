package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.Sale;

import java.util.List;

public interface SaleService {
    SaleResponse newSale(SaleRequest saleRequest);
    List<SaleResponse> getSales();

    //SaleDto getSalePerTable(Long tableId);
    SaleResponse getSalePerTable(Long tableId);
}
