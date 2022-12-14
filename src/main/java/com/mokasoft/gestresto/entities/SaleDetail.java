package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private BigDecimal discount;
    private BigDecimal benefit;
    @ManyToOne
    private Sale sale;
    @ManyToOne
    private Product product;

}
