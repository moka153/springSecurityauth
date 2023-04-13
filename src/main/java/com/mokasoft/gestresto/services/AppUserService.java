package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppUserRequest;
import com.mokasoft.gestresto.dtos.AppUserResponse;

import java.util.List;

public interface AppUserService {
    AppUserResponse saveUser(AppUserRequest appUserRequest);
    AppUserResponse updateUser(AppUserRequest appUserRequest,Long userId);
    void deleteUser(Long userId);
    AppUserResponse loadUserByUsername(String username);
    List<AppUserResponse> getAllUsers();
    void addRoleToUser(String userName,String roleName);
}
