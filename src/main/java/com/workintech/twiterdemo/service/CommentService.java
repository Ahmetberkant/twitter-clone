package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.entity.Comment;
import java.util.List;

public interface CommentService {
    Comment createComment(Long tweetId, String content, String username);
    void deleteComment(Long commentId, String username);
    List<Comment> findCommentsByTweetId(Long tweetId);
}