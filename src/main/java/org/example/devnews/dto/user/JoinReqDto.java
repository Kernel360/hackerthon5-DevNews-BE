package org.example.devnews.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.devnews.domain.user.User;
import org.example.devnews.domain.user.UserEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public  class JoinReqDto{
    // 영문, 숫자는 되고, 길이 최소 2-20자 이내
    @NotEmpty
    private String username;

    // 길이 4-20
    @Size(min = 4, max = 20)
    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    private String email;

    private List<String> categories;

    private List<String> companies;


    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(UserEnum.USER)
                .build();
    }
}