package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Product;
import lombok.Data;

@Data
public class OptionDto {
    private Long optionId;
    private String name;
    private ProductDto productDto;
}
