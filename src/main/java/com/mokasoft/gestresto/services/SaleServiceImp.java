package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.*;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.AppTableRepository;
import com.mokasoft.gestresto.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SaleServiceImp implements SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final SaleDetailService saleDetailService;

    private final AppTableRepository appTableRepository;
    private final AppTableService appTableService;


    @Override
    public SaleResponse newSale(SaleRequest saleRequest) {
        List<SaleDetailRequest> saleDetailList = saleRequest.getSaleDetailRequestList();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal benefit = BigDecimal.ZERO;
        for(SaleDetailRequest sdr : saleDetailList){
            amount = amount.add(sdr.getUnitPrice().multiply(sdr.getQuantity()));
            BigDecimal benefitPerProduct = (sdr.getUnitPrice()
                    .subtract(sdr.getCostPrice())).multiply(sdr.getQuantity());
            benefit = benefit.add(benefitPerProduct);
        }
        Sale sale = saleMapper.saleRequestToSale(saleRequest);
        sale.setSaleDate(new Date());
        sale.setAmount(amount);
        sale.setBenefit(benefit);
        Sale savedSale = saleRepository.save(sale);
        if(savedSale.getSaleId() != null){
            List<SaleDetail> saleDetails = sale.getSaleDetails();
            for(SaleDetail s : saleDetails){
                s.setSale(sale);
                saleDetailService.saveSaleDetail(s);
            }
            appTableRepository.availableTable(saleRequest.getAppTableDto().getTableId(),
                    false,saleRequest.getAppTableDto().getCustomerNumber(),savedSale);
        }
        return saleMapper.saleToSaleResponse(sale);
    }

    @Override
    public List<SaleResponse> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleResponse> saleResponses = sales.stream()
                .map(sale -> saleMapper.saleToSaleResponse(sale)).collect(Collectors.toList());
        return saleResponses;
    }
    @Override
    public SaleResponse getSalePerTable(Long tableId) {
        Sale sale = saleRepository.findByAppTable(tableId);
        SaleResponse saleResponse = saleMapper.saleToSaleResponse(sale);

        return saleResponse;
    }

}
