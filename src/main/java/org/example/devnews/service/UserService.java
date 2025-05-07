package org.example.devnews.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.devnews.domain.category.CategoryEnum;
import org.example.devnews.domain.category.CategoryRepository;
import org.example.devnews.domain.company.CompanyEnum;
import org.example.devnews.domain.company.CompanyRepository;
import org.example.devnews.domain.interestcategory.InterestCategory;
import org.example.devnews.domain.interestcategory.InterestCategoryRepository;
import org.example.devnews.domain.interestcompany.InterestCompany;
import org.example.devnews.domain.interestcompany.InterestCompanyRepository;
import org.example.devnews.domain.user.User;
import org.example.devnews.domain.user.UserRepository;
import org.example.devnews.dto.user.JoinReqDto;
import org.example.devnews.dto.user.JoinRespDto;
import org.example.devnews.ex.CustomApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final InterestCategoryRepository interestCategoryRepository;
    private final InterestCompanyRepository interestCompanyRepository;


    @Transactional
    public JoinRespDto join(JoinReqDto joinReqDto) {
        // 1. 동일 유저네임 존재 검사
        Optional<User> userOP = userRepository.findByEmail(joinReqDto.getUsername());
        if (userOP.isPresent()) {
            // 유저네임 중복
            throw new CustomApiException("동일한 username이 존재합니다.");
        }

        // 2. 패스워드 인코딩
        User userPS = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        for (String category : joinReqDto.getCategories()) {
            interestCategoryRepository.save(InterestCategory.builder()
                    .userId(userPS.getId())
                    .categoryId(categoryRepository.findByName(CategoryEnum.fromValue(category)).getId())
                      .build()
            );
        }

        for(String company : joinReqDto.getCompanies()) {
            interestCompanyRepository.save(InterestCompany.builder()
                    .userId(userPS.getId())
                    .companyId(companyRepository.findByName(CompanyEnum.fromValue(company)).getId())
                    .build()
            );
        }

        // 3. dto 응답
        return new JoinRespDto(userPS);
    }

}
