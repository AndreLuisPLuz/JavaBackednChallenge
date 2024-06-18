package com.challenge.interfaces.services;

import java.util.Map;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

public interface JwtTokenService {
    Result<String> generate(User userDetails);
    Result<DecodedJWT> validate(String token, Map<String, String> claimsList);
}
