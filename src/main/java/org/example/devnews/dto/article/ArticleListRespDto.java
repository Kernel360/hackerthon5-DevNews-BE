package org.example.devnews.dto.article;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleListRespDto {

    private final List<ArticleDto> articles;
    private final int count;
    private final int page;

    @Builder
    public ArticleListRespDto(List<ArticleDto> articles, int count, int page) {
        this.articles = articles;
        this.count = count;
        this.page = page;
    }
}
