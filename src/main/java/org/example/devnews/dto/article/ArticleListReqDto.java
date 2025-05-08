package org.example.devnews.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ArticleListReqDto {

    private final String companyName;
    private final String category;
    private final Long page;
    private final Long count;
    private final String type;

    @Builder
    public ArticleListReqDto(String companyName, String category, Long page, Long count, String type) {
        this.companyName = companyName;
        this.category = category;
        this.page = page;
        this.count = count;
        this.type = type;
    }
}
