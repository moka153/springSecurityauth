package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SaleServiceImp implements SaleService {
    private SaleRepository saleRepository;
    private SaleMapper saleMapper;

    @Override
    public SaleDto newSale(SaleDto saleDto) {
        Sale sale = saleMapper.fromSaleDto(saleDto);
        Sale newSale = saleRepository.save(sale);
        return saleMapper.fromSale(newSale);
    }

    @Override
    public List<SaleDto> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDto> saleDtos = sales.stream().map(sale -> saleMapper.fromSale(sale)).collect(Collectors.toList());
        return saleDtos;
    }
}
