package com.challenge.interfaces.services;

import com.challenge.datatransfer.LoginDTO;
import com.challenge.datatransfer.payload.LoginPayload;
import com.challenge.interfaces.structure.Result;

public interface LoginService {
    public Result<LoginDTO> login(LoginPayload payload);
}
