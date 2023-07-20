package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.Unite;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
    @NotBlank(message = "article name is required")
    private String designation;
    @Min(value = 0, message = "price should be greater than 0")
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal alertStock;
    private Unite unite;
}
