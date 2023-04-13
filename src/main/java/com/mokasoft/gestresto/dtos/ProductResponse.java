package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String designation;
    private String description;
    private ProductType type;
    private BigDecimal costPrice;
    private BigDecimal price;
    private String picture;
    private int quantity;
    private CategoryResponse category;
    private boolean available;
    private boolean isTodaySpecial;
}
