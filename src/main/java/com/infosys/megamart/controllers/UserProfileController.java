package com.infosys.megamart.controllers;

import com.infosys.megamart.dtos.Response;
import com.infosys.megamart.models.User;
import com.infosys.megamart.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserServices userServices;

    @GetMapping("")
    public ResponseEntity<Response> getUser(Authentication authentication){
        String userEmail = authentication.getName();

        User user = userServices.findUserByEmail(userEmail);

        Response response = new Response(200, user.toString());
        return ResponseEntity.ok(response);
    }
}