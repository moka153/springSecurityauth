package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeal;
    @Column(unique = true)
    private String mealName;
    @Column(columnDefinition = "text")
    private String mealDescription;
    private BigDecimal costPrice;
    private BigDecimal price;
    private String photo;
    @ManyToOne
    private Category category;

}
