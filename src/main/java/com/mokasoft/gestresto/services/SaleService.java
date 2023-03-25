package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.entities.Sale;

import java.util.List;

public interface SaleService {
    SaleDto newSale(SaleDto saleDto);
    List<SaleDto> getSales();

    SaleDto getSalePerTable(Long tableId);
}
