package com.landingis.api.controller;

import com.landingis.api.dto.AuthenticationDto;
import com.landingis.api.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtils jwtUtil;

    @GetMapping("/token")
    public ResponseEntity<AuthenticationDto> generateToken() {
        AuthenticationDto response = new AuthenticationDto(jwtUtil.generateTokenWithoutUser(), "", new ArrayList<>());

        return ResponseEntity.ok(response);
    }
}
