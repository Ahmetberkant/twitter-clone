package com.workintech.twiterdemo.repository;

import com.workintech.twiterdemo.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}