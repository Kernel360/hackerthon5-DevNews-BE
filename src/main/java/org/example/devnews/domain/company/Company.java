package org.example.devnews.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.devnews.domain.category.CategoryEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "company_tb")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyEnum name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private String logo;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Builder
    public Company(Long id, CompanyEnum name, String url, String logo, LocalDateTime createdAt, LocalDateTime updatedAt, CompanyType companyType) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.logo = logo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.companyType = companyType;
    }
}
