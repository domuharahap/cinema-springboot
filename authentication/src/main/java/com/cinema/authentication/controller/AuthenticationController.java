package com.cinema.authentication.controller;

import com.cinema.authentication.domain.Roles;
import com.cinema.authentication.domain.Users;
import com.cinema.authentication.service.AuthenticationService;
import com.cinema.authentication.utils.JwtAuthResponse;
import com.cinema.authentication.utils.JwtProvider;
import com.cinema.authentication.utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    private static final String HEADER = "Origin, X-Requested-With, Content-Type, Accept";
    private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";

    @Autowired
    AuthenticationService service;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/hallo")
    public ResponseEntity<String> hallo(){
        return ResponseEntity.ok().body("hallo world!!!");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Users users){
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        //final UserDetails userDetails = service.loadUserByUsername(users.getUsername());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtProvider.generateToken(auth);
        //System.out.println("jwt: " + jwt);
        return ResponseEntity.ok(new JwtAuthResponse (jwt, "Bearer"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody Users user){
        System.out.println("call sign up method");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        if(service.checkExistUsernameAndEmail(user.getUsername(), user.getEmail())) {
            System.out.println("Username email exist!");
            return ResponseEntity.badRequest().build();
        }
        /*SetPass*/
        user.setPassword(encoder.encode(user.getPassword()));
        //Set Roles
        Roles userRole = service.findRolesByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ApplicationContextException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        service.saveUser(user);
        return ResponseEntity.ok().headers(responseHeaders).body(user);
    }

}
