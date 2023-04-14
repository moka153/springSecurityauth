package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<Object> saveSale(@RequestBody SaleRequest saleRequest) {
        return ResponseHandler.responseBuilder("sale created", HttpStatus.CREATED,
                saleService.saveSale(saleRequest));
    }

    @DeleteMapping("/{saleId}")
    public void deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllSales() {
        return ResponseEntity.ok().body(saleService.getAllSales());
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<Object> updateSale(@RequestBody SaleRequest saleRequest, @PathVariable Long saleId) {
        return ResponseHandler.responseBuilder("sale updated", HttpStatus.OK,
                saleService.updateSale(saleRequest, saleId));
    }

    @PutMapping("/validation/{saleId}")
    public void saleValidation(@PathVariable Long saleId) {
        saleService.saleValidation(saleId);
    }
}
