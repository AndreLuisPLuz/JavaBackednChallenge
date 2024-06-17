package com.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.interfaces.services.TestPasswordService;
import com.challenge.interfaces.structure.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TestPasswordController {
    @Autowired
    TestPasswordService service;

    @PostMapping("/password/test")
    public ResponseEntity<Result<Boolean>> createPassword(@RequestBody String plainPassword) {
        var result = service.savePassword(plainPassword);
        
        return switch (result) {
            case Result.Ok<Boolean> r -> ResponseEntity.ok().body(r);
            case Result.Error<Boolean> error -> ResponseEntity.internalServerError().body(error);
        };
    }
    
    @GetMapping("/password/test")
    public ResponseEntity<Result<Boolean>> validatePassword(@RequestBody String plainPassword) {
        var result = service.validatePassword(plainPassword);

        return switch(result) {
            case Result.Ok<Boolean> r -> ResponseEntity.ok().body(r);
            case Result.Error<Boolean> error -> ResponseEntity.internalServerError().body(error);
        };
    }

    @GetMapping("/password")
    public ResponseEntity<Result<List<String>>> getAllPasswords() {
        var result = service.getAllPasswords();
        return ResponseEntity.ok().body(result);
    }
}
