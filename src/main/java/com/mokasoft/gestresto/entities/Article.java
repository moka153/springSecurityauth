package com.mokasoft.gestresto.entities;

import com.mokasoft.gestresto.enums.Unite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @Column(unique = true)
    private String designation;
    @Column(scale = 2)
    private BigDecimal price;
    @Column(scale = 3)
    private BigDecimal quantity;
    @Column(scale = 3)
    private BigDecimal alertStock;
    private Unite unite;


}
