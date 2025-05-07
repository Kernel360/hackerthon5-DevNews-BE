package org.example.devnews.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public JoinRespDto join(JoinReqDto joinReqDto) {
        // 1. 동일 유저네임 존재 검사
        Optional<User> userOP = userRepository.findByUsername(joinReqDto.getUsername());
        if (userOP.isPresent()) {
            // 유저네임 중복
            throw new CustomApiException("동일한 username이 존재합니다.");
        }

        // 2. 패스워드 인코딩
        User userPS = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        // 3. dto 응답
        return new JoinRespDto(userPS);
    }

}
