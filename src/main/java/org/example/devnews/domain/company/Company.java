package org.example.devnews.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

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
    public Company(Long id, String name, String url, String logo, LocalDateTime createdAt, LocalDateTime updatedAt, CompanyType companyType) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.logo = logo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.companyType = companyType;
    }
}
