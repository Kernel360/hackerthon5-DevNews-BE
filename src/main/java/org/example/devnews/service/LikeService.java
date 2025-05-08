package org.example.devnews.service;

import lombok.RequiredArgsConstructor;
import org.example.devnews.domain.like.Like;
import org.example.devnews.domain.like.LikeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;


    public String like(Long userId, Long articleId) {
        likeRepository.save(
                Like.builder()
                        .userId(userId)
                        .articleId(articleId)
                        .build()
        );
        return userId + " " + articleId + " liked";
    }

    public String disLike(Long userId, Long articleId) {
        Like like = likeRepository.findByUserIdAndArticleId(userId, articleId);

        likeRepository.delete(like);
        return userId + " " + articleId + " disLiked";
    }

    public boolean isLiked(Long userId, Long articleId) {
        Like like = likeRepository.findByUserIdAndArticleId(userId, articleId);
        if(like == null){
            return false;
        }
        return true;
    }

}
