package org.example.devnews.service;

import lombok.RequiredArgsConstructor;
import org.example.devnews.domain.article.Article;
import org.example.devnews.domain.article.ArticleRepository;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.CompanyRepository;
import org.example.devnews.domain.company.CompanyType;
import org.example.devnews.domain.like.LikeRepository;
import org.example.devnews.dto.article.ArticleDto;
import org.example.devnews.dto.article.ArticleListReqDto;
import org.example.devnews.dto.article.ArticleListRespDto;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
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

        // JOIN을 해서 Dto로 프로젝션 해야될거 같긴한데 해커톤이라 그냥 N + 1로
        List<ArticleDto> articleList = new ArrayList<>();
        CompanyType companyType = CompanyType.fromValue(articleListReqDto.getType());
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(articleListReqDto.getPage()), Math.toIntExact(articleListReqDto.getCount()), Sort.by(Sort.Direction.DESC, "id"));
        Slice<Article> articles = articleRepository.findByCompanyType(companyType,(Pageable)pageRequest);
        articles.forEach(article -> {
            articleList.add( ArticleDto.builder()
                    .title(article.getTitle())
                    .category(categoryRepository.findById(article.getCategoryId()).get().getName().getValue())
                    .companyLogo(companyRepository.findById(article.getCompanyId()).get().getLogo())
                    .url(article.getUrl())
                    .publishDate(article.getPublishedDate())
                    .like(likeRepository.countByArticleId(article.getId()))
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
