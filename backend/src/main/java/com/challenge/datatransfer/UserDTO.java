package com.challenge.datatransfer;

import com.challenge.models.User;

public record UserDTO(Long id, String username, String email) {
    public static UserDTO buildFromEntity(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }
}
