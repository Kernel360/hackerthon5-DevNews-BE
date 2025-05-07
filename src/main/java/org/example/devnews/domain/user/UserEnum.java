package org.example.devnews.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserEnum {
    ADMIN("관리자"), USER("사용자");

    private String value;
}
