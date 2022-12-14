package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.entities.Sale;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

}
