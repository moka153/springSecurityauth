package com.mokasoft.gestresto.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppRoleResponse {
    private Long id;
    private String roleName;
}
