package com.challenge.interfaces.services;

import java.util.List;

import com.challenge.interfaces.structure.Result;
import com.challenge.models.User;

public interface UserService {
    Result<User> createUser(User user);
    List<User> fetchAllUsers();
}
