package org.example.devnews.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("""
        SELECT COUNT(l)
        FROM Like l
        WHERE l.articleId = :articleId
    """)
    Long countByArticleId(@Param("articleId") Long articleId);

    Like findByUserIdAndArticleId(Long userId, Long articleId);



    @Query(value = """
        SELECT article_id
        FROM likes
        WHERE created_at >= NOW() - INTERVAL 1 DAY
        GROUP BY article_id
        ORDER BY COUNT(*) DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Long> findTop5ArticleIdsInLastDay();


}
