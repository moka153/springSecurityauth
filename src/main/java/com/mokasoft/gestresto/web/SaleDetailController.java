package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saledetail")
@RequiredArgsConstructor
public class SaleDetailController {
    private final SaleDetailService saleDetailService;

    @GetMapping("/{sale}")
    public List<SaleDetailResponse> getDetailPerSale(@PathVariable Sale sale){
        return saleDetailService.getSaleDetail(sale);
    }
}
