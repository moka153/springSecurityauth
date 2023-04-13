package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.AppRoleRequest;
import com.mokasoft.gestresto.dtos.AppUserRequest;
import com.mokasoft.gestresto.dtos.AppUserResponse;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.AppUserMapper;
import com.mokasoft.gestresto.repositories.AppRoleRepository;
import com.mokasoft.gestresto.repositories.AppUserRepository;
import com.mokasoft.gestresto.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final AppRoleRepository appRoleRepository;
    @Override
    public AppUserResponse saveUser(AppUserRequest appUserRequest) {
        try{
            String password = appUserRequest.getPassword();
            appUserRequest.setPassword(passwordEncoder.encode(password));
            AppUser appUser = appUserMapper.appUserRequestToAppUser(appUserRequest);
            AppUser savedAppUser = appUserRepository.save(appUser);
            return appUserMapper.appUserToAppUserResponse(savedAppUser);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("username already exists");
        }
    }

    @Override
    public AppUserResponse updateUser(AppUserRequest appUserRequest, Long userId) {
        if(!appUserRepository.findById(userId).isPresent()){
            throw new NotFoundException("User with id: "+userId+" not found");
        }
        try{
            String password = appUserRequest.getPassword();
            appUserRequest.setPassword(passwordEncoder.encode(password));
            AppUser appUser = appUserMapper.appUserRequestToAppUser(appUserRequest);
            appUser.setId(userId);
            AppUser savedAppUser = appUserRepository.save(appUser);
            return appUserMapper.appUserToAppUserResponse(savedAppUser);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("username already exists");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if(!appUserRepository.findById(userId).isPresent()){
            throw new NotFoundException("User with id: "+userId+" not found");
        }
        try{
            appUserRepository.deleteById(userId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("User can not be deleted, user already used");
        }
    }

    @Override
    public AppUserResponse loadUserByUsername(String username) {
        if(appUserRepository.findByUsername(username) == null){
            throw new NotFoundException("User : "+username+" not found");
        }
        return appUserMapper
                .appUserToAppUserResponse(appUserRepository.findByUsername(username));
    }

    @Override
    public List<AppUserResponse> getAllUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        List<AppUserResponse> appUserResponses = appUsers.stream()
                .map(appUser -> appUserMapper.appUserToAppUserResponse(appUser))
                .collect(Collectors.toList());
        return appUserResponses;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        if(appUserRepository.findByUsername(userName) == null){
            throw new NotFoundException("User not found");
        }
        if(appRoleRepository.findByRoleName(roleName) == null){
            throw new NotFoundException("Role not found");
        }
        AppUser appUser = appUserRepository.findByUsername(userName);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }
}
