package com.workintech.twiterdemo.repository;

import com.workintech.twiterdemo.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {


    Optional<Retweet> findByUserIdAndOriginalTweetId(Long userId, Long originalTweetId);
}