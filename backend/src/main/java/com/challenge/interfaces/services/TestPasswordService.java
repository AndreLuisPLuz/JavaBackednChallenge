package com.challenge.interfaces.services;

import java.util.List;

import com.challenge.interfaces.structure.Result;

public interface TestPasswordService {
    Result<Boolean> savePassword(String plainPassword);
    Result<Boolean> validatePassword(String plainPassword);
    Result<List<String>> getAllPasswords();
}
