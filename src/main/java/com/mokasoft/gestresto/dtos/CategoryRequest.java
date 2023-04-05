package com.mokasoft.gestresto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    @NotNull(message = "Veuillez renseigner le nom de la catégorie")
    @NotBlank(message = "Veuillez renseigner le nom de la catégorie")
    private String name;
    private String picture;
}
