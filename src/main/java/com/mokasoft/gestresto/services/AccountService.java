package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String userName,String roleName);
    AppUser loadUserByUserName(String userName);
    List<AppUser> listUsers();


}
