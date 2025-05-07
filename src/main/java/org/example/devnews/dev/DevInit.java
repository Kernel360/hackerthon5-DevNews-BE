package org.example.devnews.dev;


import org.example.devnews.domain.category.Category;
import org.example.devnews.domain.category.CategoryEnum;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.Company;
import org.example.devnews.domain.company.CompanyEnum;
import org.example.devnews.domain.company.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevInit {

    @Profile("dev")
    @Bean
    CommandLineRunner init(CompanyRepository companyRepository, CategoryRepository categoryRepository) {
        return (args) ->{
            System.out.println("dev init");
            for(CompanyEnum company : CompanyEnum.values()) {
                companyRepository.save(
                        Company.builder()
                                .name(company) // 또는 .getValue()
                                .build()
                );
            }
            for(CategoryEnum category : CategoryEnum.values()) {
                categoryRepository.save(
                        Category.builder()
                                .name(category)
                                .build()
                );
            }
        };
    }
}
