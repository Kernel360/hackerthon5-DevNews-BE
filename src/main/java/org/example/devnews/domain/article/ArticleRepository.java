package org.example.devnews.domain.article;

import org.example.devnews.domain.company.CompanyType;
import org.example.devnews.dto.article.MailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("""
        SELECT a
        FROM Article a
        WHERE a.companyId IN (
            SELECT c.id FROM Company c WHERE c.companyType = :type
        )
    """)
    Page<Article> findByCompanyType(@Param("type") CompanyType type, Pageable pageable);


    @Query("""
        SELECT a
        FROM Article a
        WHERE a.companyId IN (
            SELECT c.id FROM Company c WHERE c.companyType = :type
        ) AND a.categoryId = :categoryId
    """)
    Page<Article> findByCompanyTypeAndCategory(@Param("type") CompanyType type, @Param("categoryId")Long categoryId ,Pageable pageable);

    @Query("""
        SELECT a
        FROM Article a
        WHERE a.companyId IN (
            SELECT c.id FROM Company c WHERE c.companyType = :type
        ) AND a.companyId = :companyId
    """)
    Page<Article> findByCompanyTypeAndCompany(@Param("type") CompanyType type, @Param("companyId")Long companyId ,Pageable pageable);

    @Query("""
        SELECT a
        FROM Article a
        WHERE a.companyId IN (
            SELECT c.id FROM Company c WHERE c.companyType = :type
        )   AND a.categoryId = :categoryId
            AND a.companyId = :companyId
    """)
    Page<Article> findByCompanyTypeAndCategoryAndCompany(@Param("type") CompanyType type, @Param("categoryId")Long categoryId ,@Param("companyId")Long companyId ,Pageable pageable);

    List<Article> findTop3ByOrderByPublishedDateDesc();

    @Query("""
        SELECT new org.example.devnews.dto.article.MailDto(a.url, a.title, c.name, a.summary)
        FROM Article a
        JOIN Company c ON a.companyId = c.id
        WHERE a.categoryId IN :categoryId
        ORDER BY a.publishedDate DESC
    """)
    List<MailDto> findByCategoryId(@Param("categoryId") List<Long> categoryId, Pageable pageable);

    @Query("""
        SELECT new org.example.devnews.dto.article.MailDto(a.url, a.title, c.name, a.summary)
        FROM Article a
        JOIN Company c ON a.companyId = c.id
        WHERE a.companyId IN :companyId
        ORDER BY a.publishedDate DESC
    """)
    List<MailDto> findByCompanyId(@Param("companyId")List<Long> companyId, Pageable pageable);
}
