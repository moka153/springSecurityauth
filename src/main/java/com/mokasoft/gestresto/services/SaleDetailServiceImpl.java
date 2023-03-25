package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.repositories.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;
    @Override
    public SaleDetail saveSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }
}
