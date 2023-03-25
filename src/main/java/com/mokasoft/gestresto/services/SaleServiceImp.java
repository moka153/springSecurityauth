package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private final AppTableService appTableService;

    @Override
    public SaleDto newSale(SaleDto saleDto) {
        List<SaleDetail> saleDetailList = saleDto.getSaleDetails();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal benefit = BigDecimal.ZERO;
        for(SaleDetail saleDetail : saleDetailList){
            amount = amount.add(saleDetail.getUnitPrice().multiply(saleDetail.getQuantity()));
            BigDecimal benefitPerProduct = (saleDetail.getUnitPrice()
                    .subtract(saleDetail.getProduct().getCostPrice())).multiply(saleDetail.getQuantity());
            benefit = benefit.add(benefitPerProduct);
        }
        Sale sale = saleMapper.fromSaleDto(saleDto);
        sale.setAmount(amount);
        sale.setBenefit(benefit);
        Sale savedSale = saleRepository.save(sale);
        if(savedSale.getSaleId() != null){

            List<SaleDetail> saleDetails = savedSale.getSaleDetails();
            for(SaleDetail s : saleDetails){
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setSale(savedSale);
                saleDetail.setUnitPrice(s.getUnitPrice());
                saleDetail.setQuantity(s.getQuantity());
                saleDetail.setProduct(s.getProduct());

                saleDetailService.saveSaleDetail(saleDetail);
            }
            savedSale.setSaleDetails(saleDetails);
            AppTableDto appTableDto = new AppTableDto();
            appTableDto.setTableId(savedSale.getAppTable().getTableId());
            appTableDto.setCustomerNumber(savedSale.getAppTable().getCustomerNumber());
            appTableDto.setAvailable(false);
            appTableDto.setTableNumber(savedSale.getAppTable().getTableNumber());
            appTableDto.setRoom(savedSale.getAppTable().getRoom());
            appTableDto.setAppUser(savedSale.getAppUser());
            appTableDto.setSale(savedSale);
            appTableService.updateTable(appTableDto);
        }
        return saleMapper.fromSale(savedSale);
    }

    @Override
    public List<SaleDto> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDto> saleDtos = sales.stream().map(sale -> saleMapper.fromSale(sale)).collect(Collectors.toList());
        return saleDtos;
    }

    @Override
    public SaleDto getSalePerTable(Long tableId) {
        Sale sale = saleRepository.findByAppTable(tableId);
        SaleDto saleDto = saleMapper.fromSale(sale);
        return saleDto;
    }
}
