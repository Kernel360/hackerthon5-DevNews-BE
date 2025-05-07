package org.example.devnews.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.example.devnews.domain.user.User;
import org.example.devnews.util.CustomDateUtil;

@Getter
@Setter
public class LoginRespDto{
    private Long id;
    private String username;
    private String createdAt;

    public LoginRespDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.createdAt = CustomDateUtil.toStringFormat(user.getCreatedAt());
    }
}