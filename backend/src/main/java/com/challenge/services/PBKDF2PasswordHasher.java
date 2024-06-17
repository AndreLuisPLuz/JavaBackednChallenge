package com.challenge.services;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.challenge.exceptions.BadHashConfigurationException;
import com.challenge.interfaces.services.PasswordHasherService;

public class PBKDF2PasswordHasher implements PasswordHasherService {
    public String hash(String plainPassword) throws BadHashConfigurationException {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        int iterations = 65536;

        String generatedHash;

        try {
            KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), salt, iterations, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] signature = factory.generateSecret(spec).getEncoded();

            generatedHash = String.format("%s:%s:%s", iterations, toHex(salt), toHex(signature));
        } catch (NoSuchAlgorithmException ex) {
            throw new BadHashConfigurationException("Algorithm given in hash configuration doesn't exist.");
        } catch (InvalidKeySpecException ex) {
            throw new BadHashConfigurationException("Generated hash was invalid, likely due to bad configuration of the hasher.");
        }

        return generatedHash;
    }
    
    public boolean validate(String plainPassword, String originalHash) throws BadHashConfigurationException {
        String[] params = originalHash.split(":");
        
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] signature = fromHex(params[2]);
        
        PBEKeySpec spec = new PBEKeySpec(
            plainPassword.toCharArray(),
            salt,
            iterations,
            signature.length * 8
        );

        byte[] validationSignature;

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            validationSignature = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new BadHashConfigurationException("Algorithm given in hash configuration doesn't exist.");
        } catch (InvalidKeySpecException ex) {
            throw new BadHashConfigurationException("Generated hash was invalid, likely due to bad configuration of the hasher.");
        }

        return Arrays.equals(signature, validationSignature);
    }

    private static String toHex(byte[] byteArray) {
        BigInteger bi = new BigInteger(1, byteArray);
        String hex = bi.toString(16);

        int paddingLength = (byteArray.length * 2) - hex.length();

        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    private static byte[] fromHex(String hex)
    {
        byte[] byteArray = new byte[hex.length() / 2];
        for(int i = 0; i < byteArray.length ;i++)
            byteArray[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        
        return byteArray;
    }
}
