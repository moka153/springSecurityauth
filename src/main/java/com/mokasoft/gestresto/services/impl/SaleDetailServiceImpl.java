package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleDetailResponse;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.*;
import com.mokasoft.gestresto.enums.SaleStatus;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.SaleDetailMapper;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.*;
import com.mokasoft.gestresto.services.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private final AppUserRepository userRepository;
    private final AppTableRepository tableRepository;
    private final SaleMapper saleMapper;

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



    @Override
    public SaleResponse addDetail(List<SaleDetailRequest> saleDetailRequests, Long saleId) {
        if(!saleRepository.findById(saleId).isPresent()){
            throw new NotFoundException("sale not found");
        }
        for(SaleDetailRequest sd : saleDetailRequests){
            if(!productRepository.existsById(sd.getProductId())){
                throw new NotFoundException("product not found");
            }
        }


        Sale sale = saleRepository.findById(saleId).get();
        sale.setSaleId(saleId);
        BigDecimal amount = sale.getAmount();
        BigDecimal benefit = sale.getBenefit();

        for (SaleDetailRequest sd : saleDetailRequests) {
            Long productId = sd.getProductId();
            Product p = productRepository.findById(productId).get();
            BigDecimal costPrice = p.getCostPrice();
            BigDecimal price = sd.getUnitPrice();
            BigDecimal quantity = sd.getQuantity();
            amount = amount.add((price.multiply(quantity)));
            benefit = benefit.add((price.subtract(costPrice)).multiply(quantity));
        }

        sale.setAmount(amount);
        sale.setBenefit(benefit);
        
        sale.setSaleStatus(SaleStatus.IN_PROGRESS);
        Sale savedSale = saleRepository.save(sale);
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
        Sale saleResponse = saleRepository.findById(saleId).get();
        return new SaleMapper().saleToSaleResponse(saleResponse);
    }
}
