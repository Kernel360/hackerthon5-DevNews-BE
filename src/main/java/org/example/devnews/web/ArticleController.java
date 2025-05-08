package org.example.devnews.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.devnews.dto.article.ArticleListReqDto;
import org.example.devnews.dto.article.ArticleListRespDto;
import org.example.devnews.service.ArticleService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Article", description = "아티클 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public ArticleListRespDto articleList(@ModelAttribute ArticleListReqDto articleListReqDto) {
        return articleService.getArticles(articleListReqDto);
    }

    @GetMapping("/article/category")
    public ArticleListRespDto articleListCategory(@ModelAttribute ArticleListReqDto articleListReqDto) {
        return articleService.getArticlesByCategory(articleListReqDto);
    }

    @GetMapping("/article/company")
    public ArticleListRespDto articleListCompany(@ModelAttribute ArticleListReqDto articleListReqDto) {
        return articleService.getArticlesByCompany(articleListReqDto);
    }

    @GetMapping("/article/category-company")
    public ArticleListRespDto articleListCategoryCompany(@ModelAttribute ArticleListReqDto articleListReqDto) {
        return articleService.getArticlesByCategoryAndCompany(articleListReqDto);
    }

    @GetMapping("/article/new")
    public ArticleListRespDto articleNew(){
        return articleService.articleNew();
    }

    @GetMapping("/article/hot")
    public ArticleListRespDto articleHot(){ return articleService.articleHot(); }

}
