package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SaleDetailController {
    private final SaleDetailService saleDetailService;

    /*@GetMapping("/{sale}")
    public ResponseEntity<Object> getDetailPerSale(@PathVariable Sale sale){
        return ResponseEntity.ok().body(saleDetailService.getSaleDetails(sale));
    }*/
    @PostMapping("addProductSales/{saleId}")
    public ResponseEntity<Object> addProduct(@RequestBody List<SaleDetailRequest> saleDetailRequest,
                                             @PathVariable Long saleId){
        return ResponseHandler.responseBuilder("sale detail updated",HttpStatus.CREATED,
                saleDetailService.addSaleDetail(saleDetailRequest,saleId));

    }
    @PutMapping("updateProductSale/{saleDetailId}")
    public ResponseEntity<Object> updateSaleProduct(@RequestBody SaleDetailRequest saleDetailRequest,
                                                                 @PathVariable Long saleDetailId){
        return ResponseHandler.responseBuilder("sale detail updated",HttpStatus.OK,
                saleDetailService.updateSaleDetail(saleDetailRequest,saleDetailId));

    }
    @DeleteMapping("deleteProductSale/{saleDetailId}")
    public void deleteSaleDetail(@PathVariable Long saleDetailId){
        saleDetailService.deleteSaleDetail(saleDetailId);
    }

}
