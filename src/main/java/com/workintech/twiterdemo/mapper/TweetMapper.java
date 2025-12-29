package com.workintech.twiterdemo.mapper;

import com.workintech.twiterdemo.dto.TweetResponse;
import com.workintech.twiterdemo.entity.Tweet;

public class TweetMapper {

    public static TweetResponse toResponse(Tweet tweet) {
        return new TweetResponse(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getUser().getId(),
                tweet.getUser().getUsername() ,
                tweet.getUser().getFullName()
        );
    }
}