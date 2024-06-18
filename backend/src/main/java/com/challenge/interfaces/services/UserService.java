package com.challenge.interfaces.services;

import java.util.List;

import com.challenge.datatransfer.UserDTO;
import com.challenge.datatransfer.payload.UserPayload;
import com.challenge.interfaces.structure.Result;

public interface UserService {
    Result<UserDTO> createUser(UserPayload payload);
    List<UserDTO> fetchAllUsers();
}
