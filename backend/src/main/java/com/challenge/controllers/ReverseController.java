package com.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.challenge.datatransfer.ReverseResult;
import com.challenge.interfaces.services.ReverseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReverseController {
    @Autowired
    ReverseService service;

    @GetMapping("/reverse/{word}")
    public ReverseResult getMethodName(@PathVariable String word) {
        return service.reverse(word);
    }
}
