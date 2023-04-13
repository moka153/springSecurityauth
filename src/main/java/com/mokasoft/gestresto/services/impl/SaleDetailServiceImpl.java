package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.SaleDetailMapper;
import com.mokasoft.gestresto.repositories.SaleDetailRepository;
import com.mokasoft.gestresto.repositories.SaleRepository;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailMapper saleDetailMapper;

    @Override
    public List<SaleDetailResponse> getSaleDetails(Sale sale) {
        if(!saleRepository.findById(sale.getSaleId()).isPresent()){
            throw new NotFoundException("sale not found");
        }
        List<SaleDetail> saleDetails = saleDetailRepository.findBySale(sale);
        List<SaleDetailResponse> saleDetailResponses = saleDetails.stream()
                .map(saleDetail -> saleDetailMapper.saleDetailToSaleDetailResponse(saleDetail))
                .collect(Collectors.toList());
        return saleDetailResponses;
    }
}
