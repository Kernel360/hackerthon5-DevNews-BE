package org.example.devnews.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("""
        SELECT COUNT(l)
        FROM Like l
        WHERE l.articleId = :articleId
    """)
    Long countByArticleId(@Param("articleId") Long articleId);

}
