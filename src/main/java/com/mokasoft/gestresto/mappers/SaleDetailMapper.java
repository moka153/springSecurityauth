package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Product;
import com.mokasoft.gestresto.entities.SaleDetail;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SaleDetailMapper {
    public SaleDetail saleDetailRequestToSaleDetail(SaleDetailRequest saleDetailRequest){
        Product product = new Product();
        product.setProductId(saleDetailRequest.getProductId());
        SaleDetail saleDetail = SaleDetail.builder()
                .unitPrice(saleDetailRequest.getUnitPrice())
                .quantity(saleDetailRequest.getQuantity())
                .product(product)
                .note(saleDetailRequest.getNote())
                .options(saleDetailRequest.getOptions().stream()
                        .map(optionRequest -> new OptionMapper().optionRequestToOption(optionRequest))
                        .collect(Collectors.toList()))
                .build();
        return saleDetail;
    }
    public SaleDetailResponse saleDetailToSaleDetailResponse(SaleDetail saleDetail){
        SaleDetailResponse saleDetailResponse = SaleDetailResponse.builder()
                .detailId(saleDetail.getDetailId())
                .unitPrice(saleDetail.getUnitPrice())
                .quantity(saleDetail.getQuantity())
                .productId(saleDetail.getProduct().getProductId())
                .productDesignation(saleDetail.getProduct().getDesignation())
                .productPicture(saleDetail.getProduct().getPicture())
                .note(saleDetail.getNote())
                .options(saleDetail.getOptions()
                        .stream()
                        .map(option -> new OptionMapper().optionToOptionResponse(option))
                        .collect(Collectors.toList()))
                .build();
        return saleDetailResponse;
    }
}
