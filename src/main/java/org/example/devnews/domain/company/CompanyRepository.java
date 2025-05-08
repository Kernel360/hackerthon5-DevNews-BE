package org.example.devnews.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(CompanyEnum name);

    Company findByCompanyType(CompanyType type);
}
