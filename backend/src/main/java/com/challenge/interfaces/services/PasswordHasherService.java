package com.challenge.interfaces.services;

import com.challenge.exceptions.BadHashConfigurationException;

public interface PasswordHasherService {
    String hash(String plainPassword) throws BadHashConfigurationException;
    boolean validate(String plainPassword, String hash) throws BadHashConfigurationException;
}
