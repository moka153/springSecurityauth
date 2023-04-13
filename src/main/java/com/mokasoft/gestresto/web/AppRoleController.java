package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.AppRoleRequest;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.AppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class AppRoleController {
    private final AppRoleService appRoleService;

    @GetMapping
    public ResponseEntity<Object> getAllRoles(){
        return ResponseHandler.responseBuilder("Roles found",HttpStatus.FOUND,
                appRoleService.getAllAppRoles());
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<Object> getRole(@PathVariable String roleName){
        return ResponseHandler.responseBuilder("Found",HttpStatus.FOUND,
                appRoleService.getAppRoleByName(roleName));
    }
}
