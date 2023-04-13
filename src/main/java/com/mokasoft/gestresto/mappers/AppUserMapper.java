package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.AppUserRequest;
import com.mokasoft.gestresto.dtos.AppUserResponse;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppUserMapper {
    public AppUser appUserRequestToAppUser(AppUserRequest appUserRequest){
        AppUser appUser = AppUser.builder()
                .username(appUserRequest.getUsername())
                .password(appUserRequest.getPassword())
                .userStatus(appUserRequest.getUserStatus())
                .build();
        return appUser;
    }

    public AppUserResponse appUserToAppUserResponse(AppUser appUser){
        List<AppRole> roles = appUser.getAppRoles();
        AppUserResponse appUserResponse = AppUserResponse.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .userStatus(appUser.getUserStatus())
                .roles(appUser.getAppRoles())
                .build();
        return appUserResponse;
    }
}
