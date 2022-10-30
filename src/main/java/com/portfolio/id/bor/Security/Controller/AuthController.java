package com.portfolio.id.bor.Security.Controller;

import com.portfolio.id.bor.Security.DTO.JWTDTO;
import com.portfolio.id.bor.Security.DTO.NewUser;
import com.portfolio.id.bor.Security.DTO.UserLogin;
import com.portfolio.id.bor.Security.Entity.Role;
import com.portfolio.id.bor.Security.Entity.User;
import com.portfolio.id.bor.Security.Enum.RoleName;
import com.portfolio.id.bor.Security.JWT.ProviderJWT;
import com.portfolio.id.bor.Security.Service.RoleService;
import com.portfolio.id.bor.Security.Service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"", "http://localhost:4200"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserSecurityService userSecurityService;
    @Autowired
    RoleService roleService;
    @Autowired
    ProviderJWT providerJWT;

    //Create new User
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos o email inválidos"), HttpStatus.BAD_REQUEST);

        if (userSecurityService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity(new Message("Nombre de usuario ya existente"), HttpStatus.BAD_REQUEST);

        if (userSecurityService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("Email ya existente"), HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getName(), newUser.getUserName(),
                newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userSecurityService.save(user);

        return new ResponseEntity(new Message("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTDTO> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos erroneos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = providerJWT.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JWTDTO jwtdto = new JWTDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtdto, HttpStatus.OK);
    }
}
