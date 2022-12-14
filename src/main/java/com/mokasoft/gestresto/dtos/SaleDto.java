package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.SaleDetail;
import com.mokasoft.gestresto.enums.SaleType;
import lombok.Data;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class SaleDto {
    private Long saleId;
    private Date saleDate;
    private SaleType type;
    private BigDecimal amount;
    private BigDecimal benefit;
    private AppUser appUser;
    private List<SaleDetail> saleDetails = new ArrayList<>();
    private AppTableDto appTableDto;

}
