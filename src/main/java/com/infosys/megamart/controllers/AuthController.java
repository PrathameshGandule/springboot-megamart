package com.infosys.megamart.controllers;

import com.infosys.megamart.dtos.Response;
import com.infosys.megamart.dtos.LoginRequest;
import com.infosys.megamart.dtos.RegisterRequest;
import com.infosys.megamart.models.User;
import com.infosys.megamart.services.UserServices;
import com.infosys.megamart.utilities.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest registerRequest){
        if(!Objects.equals(registerRequest.password, registerRequest.confirmPassword)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Response(400, "confirm password needs to be same as password"));
        }
        String message = userServices.register(registerRequest);
        Response response = new Response(200, message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        Response response = new Response();
        User user = userServices.findUserByEmail(loginRequest.getEmail());
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            response.setStatusCode(403);
            response.setMessage("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
