package org.example.devnews.domain.interestcategory;

import org.example.devnews.domain.interestcompany.InterestCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
    @Query("""
        SELECT ic.categoryId
        FROM InterestCategory ic
        WHERE ic.userId = :userId
    """)
    List<Long> findCategoryIdByUserId(Long userId);

}
