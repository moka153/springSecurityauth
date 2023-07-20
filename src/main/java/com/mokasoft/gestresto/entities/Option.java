package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "t_options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;
    @Column(unique = true,nullable = false)
    private String name;
    private BigDecimal price;
    @ManyToMany(mappedBy = "options",fetch = FetchType.LAZY)
    private List<SaleDetail> saleDetail;
}
