package org.example.devnews.domain.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.devnews.ex.CustomApiException;

@Getter
@AllArgsConstructor
public enum CompanyEnum {
    AWS("AWS"), OLIVE_YOUNG("올리브영"), TOSS("토스"), MY_REAL_TRIP("마이리얼트립"),
    GOOGLE_DEVELOPERS("구글디벨로퍼스"), KAKAO("카카오"), KAKAO_PAY("카카오 페이"),
    GABIA("가비아"), DEVOCEAN("데보션"), CARROT_MARKET("당근마켓");

    private final String value;

    public static CompanyEnum fromValue(String value) {
        for (CompanyEnum companyEnum : CompanyEnum.values()) {
            if (companyEnum.getValue().equals(value)) {
                return companyEnum;
            }
        }
        throw new CustomApiException("유효하지 않은 회사명 입니다.");
    }
}
