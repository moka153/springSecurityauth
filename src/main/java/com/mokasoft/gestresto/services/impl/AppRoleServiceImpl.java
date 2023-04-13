package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.AppRoleRequest;
import com.mokasoft.gestresto.dtos.AppRoleResponse;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.AppRoleMapper;
import com.mokasoft.gestresto.repositories.AppRoleRepository;
import com.mokasoft.gestresto.services.AppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;
    private final AppRoleMapper appRoleMapper;

    @Override
    public AppRoleResponse saveRole(AppRoleRequest appRoleRequest) {
        AppRole appRole = appRoleMapper.appRoleRequestToAppRole(appRoleRequest);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return appRoleMapper.appRoleToAppRoleResponse(savedAppRole);
    }

    @Override
    public List<AppRoleResponse> getAllAppRoles() {
        List<AppRole> appRoles = appRoleRepository.findAll();
        List<AppRoleResponse> appRoleResponses = appRoles.stream()
                .map(appRole -> appRoleMapper.appRoleToAppRoleResponse(appRole))
                .collect(Collectors.toList());
        return appRoleResponses;
    }

    @Override
    public AppRoleResponse getAppRoleByName(String roleName) {
        if(appRoleRepository.findByRoleName(roleName) == null){
            throw new NotFoundException("role not found");
        }
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        return appRoleMapper.appRoleToAppRoleResponse(appRole);
    }
}
