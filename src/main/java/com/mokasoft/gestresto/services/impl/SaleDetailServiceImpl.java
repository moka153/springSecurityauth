package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.SaleDetailMapper;
import com.mokasoft.gestresto.repositories.ProductRepository;
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
    private final ProductRepository productRepository;

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

    @Override
    public List<SaleDetailResponse> addSaleDetail(List<SaleDetailRequest> saleDetailRequests, Long saleId) {
        if(!saleRepository.findById(saleId).isPresent()){
            throw new NotFoundException("sale not found");
        }
        for(SaleDetailRequest sd : saleDetailRequests){
            if(!productRepository.existsById(sd.getProductId())){
                throw new NotFoundException("product not found");
            }
        }
        Sale sale = new Sale();
        sale.setSaleId(saleId);
        List<SaleDetail> saleDetails = saleDetailRequests.stream()
                .map(saleDetailRequest -> saleDetailMapper.saleDetailRequestToSaleDetail(saleDetailRequest))
                .collect(Collectors.toList());
        for(SaleDetail s : saleDetails){
            s.setSale(sale);
            saleDetailRepository.save(s);
        }
        List<SaleDetailResponse> saleDetailResponses = saleDetails.stream()
                .map(saleDetail -> saleDetailMapper.saleDetailToSaleDetailResponse(saleDetail))
                .collect(Collectors.toList());
        return saleDetailResponses;
    }

    @Override
    public SaleDetailResponse updateSaleDetail(SaleDetailRequest saleDetailRequest, Long saleDetailId) {
        if(!saleDetailRepository.findById(saleDetailId).isPresent()){
            throw new NotFoundException("sale detail not found");
        }
        if(!productRepository.findById(saleDetailRequest.getProductId()).isPresent()){
            throw new NotFoundException("product not found");
        }
        SaleDetail getSaleDetail = saleDetailRepository.findById(saleDetailId).get();
        SaleDetail saleDetail = saleDetailMapper.saleDetailRequestToSaleDetail(saleDetailRequest);
        saleDetail.setDetailId(saleDetailId);
        saleDetail.setSale(getSaleDetail.getSale());
        SaleDetail updatedSaleDetail = saleDetailRepository.save(saleDetail);
        return saleDetailMapper.saleDetailToSaleDetailResponse(updatedSaleDetail);
    }

    @Override
    public void deleteSaleDetail(Long saleDetailId) {
        if(!saleDetailRepository.findById(saleDetailId).isPresent()){
            throw new NotFoundException("sale detail not found");
        }
        saleDetailRepository.deleteById(saleDetailId);
    }
}
