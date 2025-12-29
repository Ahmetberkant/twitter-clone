package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.service.RetweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;


    @PostMapping("/{tweetId}")
    public void retweet(@Valid @PathVariable Long tweetId, Principal principal) {
        retweetService.retweet(tweetId, principal.getName());
    }


    @DeleteMapping("/{tweetId}")
    public void deleteRetweet(@PathVariable Long tweetId, Principal principal) {
        retweetService.deleteRetweet(tweetId, principal.getName());
    }
}