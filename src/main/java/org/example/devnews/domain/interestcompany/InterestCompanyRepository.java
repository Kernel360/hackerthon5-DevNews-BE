package org.example.devnews.domain.interestcompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestCompanyRepository extends JpaRepository<InterestCompany, Long> {
    @Query("""
        SELECT ic.companyId
        FROM InterestCompany ic
        WHERE ic.userId = :userId
    """)
    List<Long> findCompanyIdByUserId(Long userId);
}
