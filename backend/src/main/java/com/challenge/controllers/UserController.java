package com.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.challenge.datatransfer.UserDTO;
import com.challenge.datatransfer.payload.UserPayload;
import com.challenge.interfaces.services.UserService;
import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/user")
    public ResponseEntity<Result<UserDTO>> createUser(@RequestBody UserPayload payload) {
        var result = service.createUser(payload);

        return switch(result) {
            case Result.Ok<UserDTO> r -> ResponseEntity.ok().body(r);
            case Result.Error<UserDTO> error -> ResponseEntity.internalServerError().body(error);
        };
    }
}
