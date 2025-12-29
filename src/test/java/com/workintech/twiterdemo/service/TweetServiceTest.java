package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.dto.TweetRequest;
import com.workintech.twiterdemo.dto.TweetResponse;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private TweetServiceImpl tweetService;

    private Tweet tweet;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("berkant");

        tweet = new Tweet();
        tweet.setId(10L);
        tweet.setContent("Eski Tweet İçeriği");
        tweet.setUser(user);
    }

    @Test
    @DisplayName("Tweet Güncelleme: Başarılı senaryo")
    void update_TweetExists_ReturnsUpdatedResponse() {

        TweetRequest request = new TweetRequest();
        request.setContent("Yeni Tweet İçeriği");

        when(tweetRepository.findById(10L)).thenReturn(Optional.of(tweet));


        when(tweetRepository.save(any(Tweet.class))).thenAnswer(invocation -> invocation.getArgument(0));


        TweetResponse response = tweetService.update(10L, request);


        assertNotNull(response);
        assertEquals("Yeni Tweet İçeriği", response.getContent());
        assertEquals("berkant", response.getUsername());
    }

    @Test
    @DisplayName("Tweet Güncelleme: Tweet bulunamazsa hata fırlatmalı")
    void update_TweetNotFound_ThrowsException() {
        // 1. GIVEN
        TweetRequest request = new TweetRequest();
        request.setContent("Deneme");


        when(tweetRepository.findById(99L)).thenReturn(Optional.empty());



        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tweetService.update(99L, request);
        });

        assertEquals("Tweet bulunamadı!", exception.getMessage());
    }
}