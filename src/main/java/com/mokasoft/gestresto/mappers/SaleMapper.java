package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.*;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.stream.Collectors;

@Component
public class SaleMapper {
    public Sale saleRequestToSale(SaleRequest saleRequest){
        Sale sale = Sale.builder()
                .type(saleRequest.getType())
                .saleDate(saleRequest.getSaleDate())
                .saleDetails(saleRequest.getSaleDetail()
                        .stream()
                        .map(saleDetailRequest -> new SaleDetailMapper()
                                .saleDetailRequestToSaleDetail(saleDetailRequest))
                        .collect(Collectors.toList()))
                //.saleStatus(saleRequest.getSaleStatus())
                .build();
        return sale;
    }

    public SaleResponse saleToSaleResponse(Sale sale){
        SaleResponse saleResponse = SaleResponse.builder()
                .saleId(sale.getSaleId())
                .saleDate(sale.getSaleDate())
                .type(sale.getType())
                .amount(sale.getAmount())
                .table(new AppTableMapper().appTableToAppTableResponse(sale.getAppTable()))
                .saleDetailResponses(sale.getSaleDetails()
                        .stream()
                        .map(saleDetail -> new SaleDetailMapper().saleDetailToSaleDetailResponse(saleDetail))
                        .collect(Collectors.toList()))
                .build();
        return saleResponse;
    }
}
