package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "designation is required")
    private String designation;
    private String description;
    private ProductType type;
    @Min(value = 0)
    private BigDecimal costPrice;
    @Min(value = 0)
    @NotNull(message = "price must be greater than 0")
    private BigDecimal price;
    private String picture;
    @Min(value = 0)
    private int quantity;
    @NotNull(message = "category must be specified")
    private Long categoryId;

    private boolean available = true;
    private boolean isTodaySpecial = false;
}
