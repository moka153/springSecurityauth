package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.enums.ProductType;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long productId;
    private String designation;
    private String description;
    private ProductType type;
    private BigDecimal costPrice;
    private BigDecimal price;
    private String picture;
    private int quantity;
    private Category category;

}