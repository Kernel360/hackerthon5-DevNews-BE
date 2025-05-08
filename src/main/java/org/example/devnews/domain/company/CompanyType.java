package org.example.devnews.domain.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.devnews.ex.CustomApiException;

@Getter
@AllArgsConstructor
public enum CompanyType {
    BLOG("테크 블로그"),
    NEWS("테크 뉴스");

    private final String value;

    public static CompanyType fromValue(String value){
        System.out.println("type: " + value);
        for(CompanyType type : values()){
            if(type.getValue().equals(value)){
                return type;
            }
        }
        throw new CustomApiException("유효하지 않은 타입입니다.");
    }
}
