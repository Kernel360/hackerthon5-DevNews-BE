package org.example.devnews.service;

import lombok.RequiredArgsConstructor;
import org.example.devnews.domain.article.Article;
import org.example.devnews.domain.article.ArticleRepository;
import org.example.devnews.domain.category.Category;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.Company;
import org.example.devnews.domain.company.CompanyRepository;
import org.example.devnews.domain.company.CompanyType;
import org.example.devnews.domain.like.LikeRepository;
import org.example.devnews.dto.article.ArticleDto;
import org.example.devnews.dto.article.ArticleListReqDto;
import org.example.devnews.dto.article.ArticleListRespDto;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final LikeRepository likeRepository;

    public ArticleListRespDto getArticles(ArticleListReqDto articleListReqDto) {

        // TODO
        // 쿼리로직은 개선할 것
        // JOIN을 해서 Dto로 프로젝션 해야될거 같긴한데 해커톤이라 그냥 N + 1로
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();

        List<ArticleDto> articleList = new ArrayList<>();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(articleListReqDto.getPage()), Math.toIntExact(articleListReqDto.getCount()), Sort.by(Sort.Direction.DESC, "id"));

        Page<Article> articles = articleRepository.findByCompanyType(companyType,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .category(categories.stream().filter((category -> category.getId().equals(article.getCategoryId()))).findFirst().get().getName())
                    .companyName(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getName())
                    .companyLogo(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
                    .build()
            );
        });

        return ArticleListRespDto.builder()
                .count(articles.getSize())
                .page(articles.getTotalPages())
                .articles(articleList)
                .build();

    }

    public ArticleListRespDto getArticlesByCategory(ArticleListReqDto articleListReqDto) {
        // JOIN을 해서 Dto로 프로젝션 해야될거 같긴한데 해커톤이라 그냥 N + 1로
        List<ArticleDto> articleList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        Long categoryId = categoryRepository.findByName(articleListReqDto.getCategory()).getId();
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(articleListReqDto.getPage()), Math.toIntExact(articleListReqDto.getCount()), Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findByCompanyTypeAndCategory(companyType, categoryId,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .category(categories.stream().filter((category -> category.getId().equals(article.getCategoryId()))).findFirst().get().getName())
                    .companyName(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getName())
                    .companyLogo(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
                    .build()
            );
        });

        return ArticleListRespDto.builder()
                .count(articles.getSize())
                .page(articles.getTotalPages())
                .articles(articleList)
                .build();

    }

    public ArticleListRespDto getArticlesByCompany(ArticleListReqDto articleListReqDto) {
        List<ArticleDto> articleList = new ArrayList<>();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        Long companyId = companyRepository.findByName(articleListReqDto.getCompanyName()).getId();
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(articleListReqDto.getPage()), Math.toIntExact(articleListReqDto.getCount()), Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findByCompanyTypeAndCompany(companyType, companyId,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .category(categories.stream().filter((category -> category.getId().equals(article.getCategoryId()))).findFirst().get().getName())
                    .companyName(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getName())
                    .companyLogo(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
                    .build()
            );
        });

        return ArticleListRespDto.builder()
                .count(articles.getSize())
                .page(articles.getTotalPages())
                .articles(articleList)
                .build();

    }

    public ArticleListRespDto getArticlesByCategoryAndCompany(ArticleListReqDto articleListReqDto) {
        List<ArticleDto> articleList = new ArrayList<>();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        Long categoryId = categoryRepository.findByName(articleListReqDto.getCategory()).getId();
        Long companyId = companyRepository.findByName(articleListReqDto.getCompanyName()).getId();
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(articleListReqDto.getPage()), Math.toIntExact(articleListReqDto.getCount()), Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findByCompanyTypeAndCategoryAndCompany(companyType,categoryId, companyId,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .category(categories.stream().filter((category -> category.getId().equals(article.getCategoryId()))).findFirst().get().getName())
                    .companyName(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getName())
                    .companyLogo(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
                    .build()
            );
        });

        return ArticleListRespDto.builder()
                .count(articles.getSize())
                .page(articles.getTotalPages())
                .articles(articleList)
                .build();

    }

    public ArticleListRespDto articleNew(){
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        List<ArticleDto> articleList = new ArrayList<>();
        List<Article> articles = articleRepository.findTop3ByOrderByPublishedDateDesc();

        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .category(categories.stream().filter((category -> category.getId().equals(article.getCategoryId()))).findFirst().get().getName())
                    .companyName(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getName())
                    .companyLogo(companies.stream().filter((company-> company.getId().equals(article.getCompanyId()))).findFirst().get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
                    .build()
            );
        });

        return ArticleListRespDto.builder()
                .articles(articleList)
                .build();

    }

}
