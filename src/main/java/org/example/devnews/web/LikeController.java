package org.example.devnews.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.devnews.config.security.LoginUser;
import org.example.devnews.domain.like.Like;
import org.example.devnews.dto.ResponseDto;
import org.example.devnews.dto.article.LikedDto;
import org.example.devnews.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "유저 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<?> like(@AuthenticationPrincipal LoginUser loginUser, @RequestParam Long articleId ) {
        String likeMessage = likeService.like(loginUser.getId(), articleId);
        return new ResponseEntity<>(new ResponseDto<>(1, likeMessage, null), HttpStatus.CREATED);
    }

    @DeleteMapping("/dislike")
    public ResponseEntity<?> disLike(@AuthenticationPrincipal LoginUser loginUser, @RequestParam Long articleId ) {
        String disLikeMessage = likeService.disLike(loginUser.getId(), articleId);
        return new ResponseEntity<>(new ResponseDto<>(1, disLikeMessage, null), HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<?> isLiked(@AuthenticationPrincipal LoginUser loginUser, @RequestParam Long articleId ) {
        boolean liked = likeService.isLiked(loginUser.getId(), articleId);
        return new ResponseEntity<>(new ResponseDto<>(1, null, new LikedDto(liked)), HttpStatus.OK);
    }


}

