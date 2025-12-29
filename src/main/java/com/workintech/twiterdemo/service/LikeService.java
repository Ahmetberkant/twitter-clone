package com.workintech.twiterdemo.service;

public interface LikeService {
    void likeTweet(Long tweetId, String username);
    void dislikeTweet(Long tweetId, String username);
}