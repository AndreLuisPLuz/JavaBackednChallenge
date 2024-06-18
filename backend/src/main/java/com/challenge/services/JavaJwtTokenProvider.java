package com.challenge.services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import com.challenge.interfaces.services.JwtTokenService;
import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

public class JavaJwtTokenProvider implements JwtTokenService {
    static private KeyPair keyPair;

    static private void generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        keyPair = generator.generateKeyPair();
    }

    public Result<String> generate(User userDetails) {
        if (keyPair == null) {
            try {
                generateRSAKeyPair();
            } catch(NoSuchAlgorithmException ex) {
                return new Result.Error<>("Issue generating token; invalid key generation algorithm on server-side.");
            }
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String token;
        try {
            Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
            token = JWT.create()
                .withIssuer("Andrezinho")
                .withClaim("userId", userDetails.getId().toString())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            return new Result.Error<>("Couldn't convert the claims given.");
        }

        return new Result.Ok<String>(token);
    }

    public Result<DecodedJWT> validate(String token, Map<String, String> claimsList) {
        if (keyPair == null) {
            try {
                generateRSAKeyPair();
            } catch(NoSuchAlgorithmException ex) {
                return new Result.Error<>("Issue generating token; invalid key generation algorithm on server-side.");
            }
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
        Verification verification = JWT.require(algorithm)
            .withIssuer("Andrezinho");

        for (Map.Entry<String, String> claim : claimsList.entrySet()) {
            verification = verification.withClaim(
                claim.getKey(),
                claim.getValue()
            );
        }

        try {
            JWTVerifier verifier = verification.build();
            return new Result.Ok<DecodedJWT>(verifier.verify(token));
        } catch (JWTVerificationException ex) {
            return new Result.Error<>("Invalid access token.");
        }
    }
}
