package org.example.devnews.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);

    Company findByCompanyType(CompanyType type);
}
