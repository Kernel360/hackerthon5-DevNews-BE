package org.example.devnews.dto.article;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleListReqDto {

    private final String companyName;
    private final String category;
    private final int page;
    private final int count;
    private final String type;

    @Builder
    public ArticleListReqDto(String companyName, String category, int page, int count, String type) {
        this.companyName = companyName;
        this.category = category;
        this.page = page;
        this.count = count;
        this.type = type;
    }
}
