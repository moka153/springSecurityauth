package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserResponse {
    private Long id;
    private String username;
    private UserStatus userStatus;
    private List<AppRole> roles;
}
