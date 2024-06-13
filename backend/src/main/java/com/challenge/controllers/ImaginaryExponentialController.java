package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.datatransfer.imaginarynumber.ImaginaryRequestError;
import com.challenge.interfaces.datatransfer.ImaginaryNumber;
import com.challenge.interfaces.services.ImaginaryExponentialService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ImaginaryExponentialController {
    @Autowired
    ImaginaryExponentialService service;

    @GetMapping("/imaexp")
    public ResponseEntity<ImaginaryNumber> imaginaryExponential(@RequestParam Double A, @RequestParam Double B) {
        try {
            var imaginaryNumber = service.calculateExponential(A, B);
            return ResponseEntity.ok(new ImaginaryRequestError("One or both of mandatory A and B query params were undefined or null."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                new ImaginaryRequestError("One or both of mandatory A and B query params were undefined or null.")
            );
        }
    }
}
