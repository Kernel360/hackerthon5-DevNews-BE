package org.example.devnews.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.devnews.dto.article.ArticleListReqDto;
import org.example.devnews.dto.article.ArticleListRespDto;
import org.example.devnews.service.ArticleService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Article", description = "아티클 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public ArticleListRespDto articleList(ArticleListReqDto articleListReqDto) {
        return null;

    }

}
