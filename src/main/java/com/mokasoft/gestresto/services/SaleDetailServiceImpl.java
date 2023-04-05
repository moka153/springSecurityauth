package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;
    private final SaleMapper saleMapper;
    @Override
    public SaleDetail saveSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    @Override
    public List<SaleDetailResponse> getSaleDetail(Sale sale) {
        List<SaleDetail> saleDetails = saleDetailRepository.findBySale(sale);
        List<SaleDetailResponse> saleDetailResponseList = saleDetails.stream()
                .map(saleDetail -> saleMapper.saleDetailToSaleDetailResponse(saleDetail))
                .collect(Collectors.toList());
        return saleDetailResponseList;
    }


}
