package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_saleDetails")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;
    private BigDecimal unitPrice;
    @Column(precision = 38,scale = 3)
    private BigDecimal quantity;
    @ManyToOne
    private Sale sale;
    @ManyToOne
    private Product product;
    @Lob
    private String note;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "SaleDetailLOptions",
            joinColumns = {
                    @JoinColumn(name = "saleDetailId",referencedColumnName = "detailId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="optionId",referencedColumnName = "optionId")
            }
    )
    private List<Option> options;

}
