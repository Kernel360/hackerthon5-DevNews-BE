package org.example.devnews.domain.article;

import org.example.devnews.domain.company.CompanyType;
import org.hibernate.query.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("""
        SELECT a
        FROM Article a
        WHERE a.companyId IN (
            SELECT c FROM Company c WHERE c.companyType = :type
        )
    """)
    Slice<Article> findByCompanyType(@Param("type") CompanyType type, Pageable pageable);



}
