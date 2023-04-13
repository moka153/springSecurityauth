package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.AppRoleRequest;
import com.mokasoft.gestresto.dtos.AppRoleResponse;
import com.mokasoft.gestresto.entities.AppRole;
import org.springframework.stereotype.Component;

@Component
public class AppRoleMapper {
    public AppRole appRoleRequestToAppRole(AppRoleRequest appRoleRequest){
        AppRole appRole = AppRole.builder()
                .id(appRoleRequest.getId())
                .roleName(appRoleRequest.getRoleName())
                .build();
        return appRole;
    }

    public AppRoleResponse appRoleToAppRoleResponse(AppRole appRole){
        AppRoleResponse appRoleResponse = AppRoleResponse.builder()
                .id(appRole.getId())
                .roleName(appRole.getRoleName())
                .build();
        return appRoleResponse;
    }
}
