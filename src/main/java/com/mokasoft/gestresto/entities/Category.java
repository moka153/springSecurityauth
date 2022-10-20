package com.mokasoft.gestresto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String name;
    private String description;
    private String photo;
    @OneToMany(mappedBy = "category")
    private List<Meal> meals;
}
