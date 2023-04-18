package com.mokasoft.gestresto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mokasoft.gestresto.enums.SaleStatus;
import com.mokasoft.gestresto.enums.SaleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "t_sales")
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date saleDate =new Date();
    @Enumerated(EnumType.STRING)
    private SaleType type;
    @ColumnDefault("0")
    private BigDecimal amount;
    @ColumnDefault("0")
    private BigDecimal benefit;
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus = SaleStatus.IN_PROGRESS;
    @ManyToOne
    private AppUser appUser;
    @OneToMany(mappedBy = "sale",cascade = CascadeType.REMOVE)
    private List<SaleDetail> saleDetails = new ArrayList<>();
    @OneToOne
    private AppTable appTable;
    @OneToMany(mappedBy = "sale",cascade = CascadeType.REMOVE)
    private List<Payment> payments;

}
