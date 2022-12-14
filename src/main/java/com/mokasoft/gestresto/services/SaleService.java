package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDto;

import java.util.List;

public interface SaleService {
    SaleDto newSale(SaleDto saleDto);
    List<SaleDto> getSales();
}
