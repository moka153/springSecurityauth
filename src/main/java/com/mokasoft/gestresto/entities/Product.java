package com.mokasoft.gestresto.entities;

import com.mokasoft.gestresto.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true)
    private String designation;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private BigDecimal costPrice;
    private BigDecimal price;
    private String picture;
    private int quantity;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Option> options;
    @OneToMany(mappedBy = "product")
    private List<SaleDetail> saleDetails;

}
