package com.mokasoft.gestresto.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.services.AccountService;
import lombok.Data;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private AccountService accountService;


    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }
    @PostMapping(path = ("/users"))
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = ("/roles"))
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = ("/addRoleToUser"))
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
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

                AppUser appUser = accountService.loadUserByUserName(username);

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

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal){
        return accountService.loadUserByUserName(principal.getName());
    }
    
  
}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
