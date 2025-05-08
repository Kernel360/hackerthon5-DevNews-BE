package org.example.devnews.domain.article;

import org.example.devnews.domain.company.CompanyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;


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
}
