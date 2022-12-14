package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SaleController {
    private SaleService saleService;
    @PostMapping(path = "/sales/newSale")
    public SaleDto newSale(@RequestBody SaleDto saleDto){
        return saleService.newSale(saleDto);
    }

    @GetMapping(path = "/sales")
    public List<SaleDto> getAllSales(){
        return saleService.getSales();
    }
}
