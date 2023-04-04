package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    @PostMapping
    public ResponseEntity<Object> newSale(@RequestBody SaleRequest saleRequest){
        //return saleService.newSale(saleRequest);
        return ResponseHandler.responseBuilder("commande passée",HttpStatus.CREATED
                ,saleService.newSale(saleRequest));
    }

    @GetMapping
    public List<SaleResponse> getAllSales(){
        return saleService.getSales();
    }


    @GetMapping("/tableId")
    public SaleResponse getSalePerTable(@PathVariable Long tableId){
        return saleService.getSalePerTable(tableId);
    }
}
