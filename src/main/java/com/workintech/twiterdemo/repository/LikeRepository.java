package com.workintech.twiterdemo.repository;

import com.workintech.twiterdemo.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {


    Optional<Like> findByUserIdAndTweetId(Long userId, Long tweetId);
}