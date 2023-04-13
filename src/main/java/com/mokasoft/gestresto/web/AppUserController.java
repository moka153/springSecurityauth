package com.mokasoft.gestresto.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokasoft.gestresto.dtos.AppUserRequest;
import com.mokasoft.gestresto.dtos.AppUserResponse;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.AppUserService;
import com.mokasoft.gestresto.utils.RoleUserForm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<Object> saveUser(@Valid @RequestBody AppUserRequest appUserRequest){
        return ResponseHandler.responseBuilder("User created", HttpStatus.CREATED,
                appUserService.saveUser(appUserRequest));
    }
    @PostMapping("/setRole")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        appUserService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return ResponseHandler.responseBuilder("users found",HttpStatus.FOUND,
                appUserService.getAllUsers());
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        appUserService.deleteUser(userId);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody AppUserRequest appUserRequest,
                                             @PathVariable Long userId){
        return ResponseHandler.responseBuilder("User Updated",HttpStatus.OK,
                appUserService.updateUser(appUserRequest,userId));
    }

    @GetMapping(path = ("/refreshToken"))
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken = request.getHeader("Authorization");
        if(authToken != null && authToken.startsWith("Bearer ")){
            try{
                String jwt = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("moka153");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();

                AppUserResponse appUserResponse = appUserService.loadUserByUsername(username);
                AppUser appUser = new AppUser();
                appUser.setId(appUserResponse.getId());
                appUser.setUsername(appUserResponse.getUsername());
                appUser.setAppRoles(appUserResponse.getRoles());

                String jwtAccesToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",appUser.getAppRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> idToken = new HashMap<>();
                idToken.put("acces-token",jwtAccesToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){
                throw e;
            }
        }else{
            throw new RuntimeException("Refresh token missed");
        }
    }

    //Renvoie un null
    @GetMapping(path = "/profile")
    public AppUserResponse profile(Principal principal){
        return appUserService.loadUserByUsername(principal.getName());
    }
}
