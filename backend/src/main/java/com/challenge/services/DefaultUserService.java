package com.challenge.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.datatransfer.UserDTO;
import com.challenge.datatransfer.payload.UserPayload;
import com.challenge.exceptions.BadHashConfigurationException;
import com.challenge.interfaces.repositories.UserJPARepository;
import com.challenge.interfaces.services.PasswordHasherService;
import com.challenge.interfaces.services.UserService;
import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

public class DefaultUserService implements UserService {
    @Autowired
    PasswordHasherService hasher;

    @Autowired
    UserJPARepository repo;

    public Result<UserDTO> createUser(UserPayload payload) {
        var user = new User();
        
        String hashedPassword;
        try {
            hashedPassword = hasher.hash(payload.password());
        } catch (BadHashConfigurationException ex) {
            return new Result.Error<>(ex.getMessage());
        }

        user.setUsername(payload.username());
        user.setEmail(payload.email());
        user.setPassword(hashedPassword);

        try {
            var returnedUser = repo.save(user);
            return new Result.Ok<UserDTO>(
                UserDTO.buildFromEntity(returnedUser)
            );
        } catch(IllegalArgumentException ex) {
            return new Result.Error<>("User given was null.");
        }
    }

    public List<UserDTO> fetchAllUsers() {
        var allUsers = repo.findAll();
        
        return allUsers.stream()
            .map(u -> UserDTO.buildFromEntity(u))
            .collect(Collectors.toList());
    }
}
