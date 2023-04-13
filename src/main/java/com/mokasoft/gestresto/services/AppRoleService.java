package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppRoleRequest;
import com.mokasoft.gestresto.dtos.AppRoleResponse;

import java.util.List;

public interface AppRoleService {
    AppRoleResponse saveRole(AppRoleRequest appRoleRequest);
    List<AppRoleResponse>  getAllAppRoles();
    AppRoleResponse getAppRoleByName(String roleName);
}
