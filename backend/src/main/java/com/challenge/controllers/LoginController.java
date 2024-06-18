package com.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.challenge.datatransfer.LoginDTO;
import com.challenge.datatransfer.payload.LoginPayload;
import com.challenge.interfaces.services.LoginService;
import com.challenge.interfaces.structure.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {
    @Autowired
    LoginService service;

    @PostMapping("/login")
    public ResponseEntity<Result<LoginDTO>> tryLogin(@RequestBody LoginPayload payload) {
        var result = service.login(payload);

        return switch(result) {
            case Result.Ok<LoginDTO> r -> ResponseEntity.ok().body(r);
            case Result.Error<LoginDTO> error -> ResponseEntity.badRequest().body(error);
        };
    }
}
