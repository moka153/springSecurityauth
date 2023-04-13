package com.mokasoft.gestresto.entities;

import com.mokasoft.gestresto.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "t_products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true,nullable = false)
    private String designation;
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @Column(precision = 19,scale = 2)
    private BigDecimal costPrice;
    @Column(precision = 19,scale = 2)
    private BigDecimal price;
    private String picture;
    @Column(precision = 19,scale = 3)
    private int quantity;
    private boolean available = true;
    private boolean isTodaySpecial;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<SaleDetail> saleDetails;

}
