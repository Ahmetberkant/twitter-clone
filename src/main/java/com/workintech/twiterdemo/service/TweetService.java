package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.dto.TweetRequest;
import com.workintech.twiterdemo.dto.TweetResponse;
import com.workintech.twiterdemo.entity.Tweet;
import java.util.List;

public interface TweetService {
    TweetResponse save(TweetRequest tweetRequest, String username);
    List<TweetResponse> findAll();
    List<TweetResponse> findAllByUserId(Long userId);
    Tweet findById(Long id);
    void delete(Long tweetId, String username);
    TweetResponse update(Long id, TweetRequest tweetRequest);
}