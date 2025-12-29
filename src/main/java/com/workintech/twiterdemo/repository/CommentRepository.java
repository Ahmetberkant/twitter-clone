package com.workintech.twiterdemo.repository;

import com.workintech.twiterdemo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTweetId(Long tweetId);
}