package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.dto.TweetRequest;
import com.workintech.twiterdemo.dto.TweetResponse;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;


    @PostMapping
    public TweetResponse save(@Valid @RequestBody TweetRequest tweetRequest, Principal principal) {
        // Principal, o an giriş yapmış olan kullanıcının bilgisini (username) tutar.
        return tweetService.save(tweetRequest, principal.getName());
    }


    @GetMapping
    public List<TweetResponse> findAll() {
        return tweetService.findAll();
    }


    @GetMapping("/findByUserId/{userId}")
    public List<TweetResponse> findAllByUserId(@PathVariable Long userId) {
        return tweetService.findAllByUserId(userId);
    }


    @GetMapping("/{id}")
    public Tweet findById(@PathVariable Long id) {
        return tweetService.findById(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        tweetService.delete(id, principal.getName());
    }

    @PutMapping("/{id}")
    public TweetResponse update(@Valid @PathVariable Long id, @RequestBody TweetRequest tweetRequest) {
        return tweetService.update(id, tweetRequest);
    }
}