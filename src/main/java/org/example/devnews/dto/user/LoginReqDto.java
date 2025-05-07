package org.example.devnews.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotEmpty
public class LoginReqDto {

    private String username;

    private String password;
}