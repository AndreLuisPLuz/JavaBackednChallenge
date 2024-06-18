package com.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.datatransfer.LoginDTO;
import com.challenge.datatransfer.payload.LoginPayload;
import com.challenge.exceptions.BadHashConfigurationException;
import com.challenge.interfaces.repositories.UserJPARepository;
import com.challenge.interfaces.services.JwtTokenService;
import com.challenge.interfaces.services.LoginService;
import com.challenge.interfaces.services.PasswordHasherService;
import com.challenge.interfaces.structure.Result;

public class DefaultLoginService implements LoginService {
    @Autowired
    private PasswordHasherService hasher;

    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    private UserJPARepository userRepo;

    public Result<LoginDTO> login(LoginPayload payload) {
        var username = payload.username();
        var matchingUsers = userRepo.findByUsername(username);

        if (matchingUsers.size() == 0) {
            return new Result.Error<>("No account matches this username.");
        }

        var user = matchingUsers.get(0);

        boolean isPasswordCorrect;
        try {
            isPasswordCorrect = hasher.validate(
                payload.password(),
                user.getPassword()
            );
        } catch(BadHashConfigurationException ex) {
            return new Result.Error<>(ex.getMessage());
        }

        if (!isPasswordCorrect) {
            return new Result.Error<>("Password does not match account.");
        }

        String token;
        switch(tokenService.generate(user)) {
            case Result.Ok<String> t -> token = t.result();
            case Result.Error<String> error -> { return new Result.Error<>(error.message()); }
        }

        var loginDto = new LoginDTO("Successful login!", token);

        return new Result.Ok<LoginDTO>(loginDto);
    }
}
