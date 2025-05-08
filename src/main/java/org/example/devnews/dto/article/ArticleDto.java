package org.example.devnews.dto.article;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleDto {
    private final Long id;
    private final String title;
    private final String companyName;
    private final String companyLogo;
    private final String url;
    private final String category;
    private final Long like;
    private final LocalDateTime publishDate;


    @Builder
    public ArticleDto(Long id, String title, String companyName, String companyLogo, String url, String category, Long like, LocalDateTime publishDate) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.url = url;
        this.category = category;
        this.like = like;
        this.publishDate = publishDate;
    }
}
