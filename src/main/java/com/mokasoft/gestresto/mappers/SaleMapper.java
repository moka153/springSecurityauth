package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.*;
import com.mokasoft.gestresto.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleMapper {


    public SaleDto fromSale(Sale sale){
        SaleDto saleDto = new SaleDto();
        BeanUtils.copyProperties(sale,saleDto);
        return saleDto;
    }

    public Sale fromSaleDto(SaleDto saleDto){
        Sale sale = new Sale();
        BeanUtils.copyProperties(saleDto,sale);
        return sale;
    }

    public SaleDetailResponse saleDetailToSaleDetailResponse(SaleDetail saleDetail){
        SaleDetailResponse saleDetailResponse = new SaleDetailResponse();
        saleDetailResponse.setUnitPrice(saleDetail.getUnitPrice());
        saleDetailResponse.setQuantity(saleDetail.getQuantity());
        saleDetailResponse.setDesignation(saleDetail.getProduct().getDesignation());
        saleDetailResponse.setProductId(saleDetail.getProduct().getProductId());

        return saleDetailResponse;
    }

    public SaleResponse saleToSaleResponse(Sale sale){
        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setSaleId(sale.getSaleId());
        saleResponse.setSaleDate(sale.getSaleDate());
        saleResponse.setType(sale.getType());
        saleResponse.setAmount(sale.getAmount());
        AppTableDto appTableDto = new AppTableDto();
        appTableDto.setTableId(sale.getAppTable().getTableId());
        appTableDto.setTableNumber(sale.getAppTable().getTableNumber());
        appTableDto.setCustomerNumber(sale.getAppTable().getCustomerNumber());
        saleResponse.setAppTableDto(appTableDto);
       /* List<SaleDetailResponse> saleDetailResponse = sale.getSaleDetails().stream()
                .map(sd -> saleDetailToSaleDetailResponse(sd))
                .collect(Collectors.toList());
        saleResponse.setSaleDetailResponses(saleDetailResponse);*/

        return saleResponse;
    }

    public Sale saleRequestToSale(SaleRequest saleRequest){
        Sale sale = new Sale();
        sale.setType(saleRequest.getType());
        AppTable appTable = new AppTable();
        appTable.setTableId(saleRequest.getAppTableDto().getTableId());
        sale.setAppTable(appTable);
        AppUser user = new AppUser();
        user.setId(saleRequest.getAppUser());
        sale.setAppUser(user);
        List<SaleDetail> saleDetails = saleRequest.getSaleDetailRequestList().stream()
                .map(saleDetailRequest -> saleDetailRequestToSaleDetail(saleDetailRequest))
                .collect(Collectors.toList());
        sale.setSaleDetails(saleDetails);
        return sale;
    }

    public SaleDetail saleDetailRequestToSaleDetail(SaleDetailRequest saleDetailRequest){
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setUnitPrice(saleDetailRequest.getUnitPrice());
        saleDetail.setQuantity(saleDetailRequest.getQuantity());
        Product product = new Product();
        product.setProductId(saleDetailRequest.getProductId());
        product.setDesignation(saleDetailRequest.getDesignation());
        product.setCostPrice(saleDetailRequest.getCostPrice());
        saleDetail.setProduct(product);
        return saleDetail;
    }
}
