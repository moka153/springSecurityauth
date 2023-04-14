package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/details")
@RequiredArgsConstructor
public class SaleDetailController {
    private final SaleDetailService saleDetailService;

    @GetMapping("/{sale}")
    public ResponseEntity<Object> getDetailPerSale(@PathVariable Sale sale){
        return ResponseEntity.ok().body(saleDetailService.getSaleDetails(sale));
    }
}
