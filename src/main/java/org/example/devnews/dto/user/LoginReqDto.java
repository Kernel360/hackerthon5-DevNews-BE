package org.example.devnews.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotEmpty
public class LoginReqDto {

    @Email
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}