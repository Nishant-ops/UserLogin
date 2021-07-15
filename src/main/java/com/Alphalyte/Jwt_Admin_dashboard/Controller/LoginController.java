package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.loginRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.JwtResponse;
import com.Alphalyte.Jwt_Admin_dashboard.security.jwt.JwtUsernameandPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUsernameandPasswordAuthenticationFilter jwtUsernameandPasswordAuthenticationFilter;

    List<String> list=new ArrayList<>();

   // private
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody loginRequest loginRequest)
    {
        final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        list.add("ROLE_ADMIN");
        System.out.println(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        user userDetails=(user) authentication.getPrincipal();
        String token=jwtUsernameandPasswordAuthenticationFilter.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(
                token, userDetails.getUsercode(), userDetails.getUsername(), userDetails.getEmail(),
                list
        ));
    }
}

