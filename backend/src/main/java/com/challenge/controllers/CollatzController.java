package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.datatransfer.CollatzEntry;
import com.challenge.datatransfer.CollatzOutput;
import com.challenge.interfaces.services.CollatzService;
import com.challenge.interfaces.structure.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CollatzController {
    @Autowired
    CollatzService service;

    @GetMapping("/collatz")
    public ResponseEntity<Result<CollatzOutput>> calculateCollatz(@RequestBody CollatzEntry payload) {
        var result = service.calculateCollatz(payload.current(), payload.step());
        
        return switch (result) {
            case Result.Ok<CollatzOutput> r -> ResponseEntity.ok().body(r);
            case Result.Error<?> error -> ResponseEntity.badRequest().body(error);
        };
    }
    
}
