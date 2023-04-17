package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/details")
@RequiredArgsConstructor
public class SaleDetailController {
    private final SaleDetailService saleDetailService;

    @GetMapping("/{sale}")
    public ResponseEntity<Object> getDetailPerSale(@PathVariable Sale sale){
        return ResponseEntity.ok().body(saleDetailService.getSaleDetails(sale));
    }
    @PostMapping("/{saleId}")
    public ResponseEntity<SaleDetailResponse> addProduct(@RequestBody SaleDetailRequest saleDetailRequest,
                                                         @PathVariable Long saleId){
        return new ResponseEntity<>(saleDetailService
                .addSaleDetail(saleDetailRequest,saleId),
                HttpStatus.CREATED);
    }
    @PutMapping("/{saleDetailId}")
    public ResponseEntity<SaleDetailResponse> updateSaleProduct(@RequestBody SaleDetailRequest saleDetailRequest,
                                                                 @PathVariable Long saleDetailId){
        return new ResponseEntity<>(saleDetailService
                .updateSaleDetail(saleDetailRequest,saleDetailId),
                HttpStatus.CREATED);
    }
    @DeleteMapping("/{saleDetailId}")
    public void deleteSaleDetail(@PathVariable Long saleDetailId){
        saleDetailService.deleteSaleDetail(saleDetailId);
    }

}
