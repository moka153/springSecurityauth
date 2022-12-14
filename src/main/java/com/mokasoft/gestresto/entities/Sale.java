package com.mokasoft.gestresto.entities;

import com.mokasoft.gestresto.enums.SaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    @Enumerated(EnumType.STRING)
    private SaleType type;
    @ColumnDefault("0")
    private BigDecimal amount;
    @ColumnDefault("0")
    private BigDecimal benefit;
    @ManyToOne
    private AppUser appUser;
    @OneToMany(mappedBy = "sale")
    private List<SaleDetail> saleDetails = new ArrayList<>();
    @ManyToOne
    private AppTable appTable;

}
