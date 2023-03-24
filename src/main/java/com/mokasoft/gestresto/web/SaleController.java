package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    @PostMapping
    public SaleDto newSale(@RequestBody SaleDto saleDto){
        return saleService.newSale(saleDto);
    }

    @GetMapping(path = "/sales")
    public List<SaleDto> getAllSales(){
        return saleService.getSales();
    }
}
