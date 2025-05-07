package org.example.devnews.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.devnews.domain.user.User;

@Getter
@Setter
@ToString
public class JoinRespDto {
    private Long id;
    private String username;

    public JoinRespDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
