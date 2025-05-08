package org.example.devnews.dev;


import org.example.devnews.domain.article.Article;
import org.example.devnews.domain.article.ArticleRepository;
import org.example.devnews.domain.category.Category;
import org.example.devnews.domain.category.CategoryEnum;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.Company;
import org.example.devnews.domain.company.CompanyEnum;
import org.example.devnews.domain.company.CompanyRepository;
import org.example.devnews.domain.company.CompanyType;
import org.example.devnews.domain.like.LikeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
public class DevInit {

    @Profile("dev")
    @Bean
    CommandLineRunner init(CompanyRepository companyRepository,
                           CategoryRepository categoryRepository,
                           ArticleRepository articleRepository,
                           LikeRepository likeRepository) {
        return (args) ->{
            System.out.println("dev init");
            for(CompanyEnum company : CompanyEnum.values()) {
                companyRepository.save(
                        Company.builder()
                                .name(company) // 또는 .getValue()
                                .url("test")
                                .companyType(CompanyType.BLOG)
                                .build()
                );
            }
            for(CategoryEnum category : CategoryEnum.values()) {
                categoryRepository.save(
                        Category.builder()
                                .name(category)
                                .build()
                );
            }
            for(int i =0 ; i < 10; i++){
                if(i %2 ==0 ){
                    articleRepository.save(
                            Article.builder()
                                    .title("test" + i)
                                    .summary("summary" + i)
                                    .url("www.naver.com")
                                    .categoryId(3L)
                                    .companyId(1L)
                                    .publishedDate(LocalDateTime.now())
                                    .build()
                    );
                }
                else {
                    articleRepository.save(
                            Article.builder()
                                    .title("test" + i)
                                    .summary("summary" + i)
                                    .url("www.naver.com")
                                    .categoryId(2L)
                                    .companyId(1L)
                                    .publishedDate(LocalDateTime.now())
                                    .build()
                    );
                }
            }
        };
    }
}
