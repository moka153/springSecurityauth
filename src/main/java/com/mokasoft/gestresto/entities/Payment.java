package com.mokasoft.gestresto.entities;

import com.mokasoft.gestresto.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private BigDecimal payment;
    @ManyToOne
    private Sale sale;
}
