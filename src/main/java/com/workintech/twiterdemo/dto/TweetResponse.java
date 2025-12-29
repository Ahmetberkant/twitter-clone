package com.workintech.twiterdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private String username;
    private String fullName;
}