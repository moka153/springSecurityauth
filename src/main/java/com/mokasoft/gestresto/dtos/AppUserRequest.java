package com.mokasoft.gestresto.dtos;

import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    private UserStatus userStatus;
    private List<AppRoleRequest> appRoleRequests;
}
