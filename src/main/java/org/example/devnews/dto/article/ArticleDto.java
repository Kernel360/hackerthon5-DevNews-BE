package org.example.devnews.dto.article;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private final String title;
    private final String companyName;
    private final String companyLogo;
    private final String url;
    private final String category;
    private final Long like;
    private final LocalDateTime publishDate;

    @Builder
    public ArticleDto(String title, String companyName, String companyLogo, String url, String category, Long like, LocalDateTime publishDate) {
        this.title = title;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.url = url;
        this.category = category;
        this.like = like;
        this.publishDate = publishDate;
    }
}
