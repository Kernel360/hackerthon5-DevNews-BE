package org.example.devnews.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.devnews.ex.CustomApiException;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
    BACKEND("백엔드"), FRONTEND("웹 프론트엔드"), CLOUD("클라우드 / 인프라"), DATA("데이터 분석"),
    AI("AI / 머신러닝"), DEVOPS("DevOps / 툴링"), MOBILE("모바일"),
    LANGUAGE("언어 / 프로그래밍"), SECURITY("보안"), CURTULE("개발 문화 / 커리어");

    private String value;

    public static CategoryEnum fromValue(String value) {
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            return categoryEnum;
        }
        throw new CustomApiException("해당하는 카테고리가 없습니다.");
    }

}
