package com.challenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.exceptions.BadHashConfigurationException;
import com.challenge.interfaces.services.PasswordHasherService;
import com.challenge.interfaces.services.TestPasswordService;
import com.challenge.interfaces.structure.Result;

public class MockPasswordService implements TestPasswordService {
    @Autowired
    PasswordHasherService hasher;

    private List<String> storedPasswordHashes = new ArrayList<>();

    public Result<Boolean> savePassword(String plainPassword) {
        try {
            var newHash = hasher.hash(plainPassword);
            storedPasswordHashes.add(newHash);

            return new Result.Ok<Boolean>(true);
        } catch (BadHashConfigurationException ex) {
            return new Result.Error<>("Bad hash configuration on the server-side caused an error.");
        }
    }

    public Result<Boolean> validatePassword(String plainPassword) {
        for (var passwordHash : storedPasswordHashes) {
            try {
                if (hasher.validate(plainPassword, passwordHash))
                    return new Result.Ok<Boolean>(true);
            } catch (BadHashConfigurationException ex) {
                return new Result.Error<>("Bad hash configuration on the server-side caused an error.");
            }
        }

        return new Result.Ok<Boolean>(false);
    }

    public Result<List<String>> getAllPasswords() {
        return new Result.Ok<List<String>>(storedPasswordHashes);
    }
}
