package com.mokasoft.gestresto.dtos;

import lombok.Data;

import javax.persistence.Column;
@Data
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String picture;
}
