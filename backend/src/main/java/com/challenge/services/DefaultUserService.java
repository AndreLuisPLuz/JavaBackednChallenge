package com.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.interfaces.repositories.UserJPARepository;
import com.challenge.interfaces.services.UserService;
import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

public class DefaultUserService implements UserService {
    @Autowired
    UserJPARepository repo;

    public Result<User> createUser(User user) {
        try {
            var returnedUser = repo.save(user);
            return new Result.Ok<User>(returnedUser);
        } catch(IllegalArgumentException ex) {
            return new Result.Error<>("User given was null.");
        }
    }

    public List<User> fetchAllUsers() {
        return repo.findAll();
    }
}
