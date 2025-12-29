package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/like/{tweetId}")
    public void likeTweet(@Valid @PathVariable Long tweetId, Principal principal) {
        likeService.likeTweet(tweetId, principal.getName());
    }


    @PostMapping("/dislike/{tweetId}")
    public void dislikeTweet(@Valid @PathVariable Long tweetId, Principal principal) {
        likeService.dislikeTweet(tweetId, principal.getName());
    }
}