package org.example.devnews.service;

import lombok.RequiredArgsConstructor;
import org.example.devnews.domain.article.Article;
import org.example.devnews.domain.article.ArticleRepository;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.CompanyRepository;
import org.example.devnews.domain.company.CompanyType;
import org.example.devnews.dto.article.ArticleDto;
import org.example.devnews.dto.article.ArticleListReqDto;
import org.example.devnews.dto.article.ArticleListRespDto;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    // 쿼리가 어려워요 ..
    public ArticleListRespDto getArticles(ArticleListReqDto articleListReqDto) {
        List<ArticleDto> articleList = new ArrayList<>();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        PageRequest pageRequest = PageRequest.of(articleListReqDto.getPage(), articleListReqDto.getCount(), Sort.by(Sort.Direction.DESC, "id"));
        Slice<Article> articles = articleRepository.findByCompanyType(companyType,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .title(article.getTitle())
                    .category(categoryRepository.findById(article.getCategoryId()).get().getName().getValue())
                    .companyLogo(companyRepository.findById(article.getCompanyId()).get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like()
                    .build()
            );
        });
        return ArticleListRespDto.builder()
                .count(articles.getSize())
                .page(articles.getNumber())
                .articles(articleList)
                .build();

    }
}
