package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.enums.Unite;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private Long articleId;
    private String designation;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal alertStock;
    private Unite unite;
}
