package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Option {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;
    private String name;
    @ManyToOne
    private Product product;
}
