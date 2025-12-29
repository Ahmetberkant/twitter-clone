package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.entity.Like;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.exception.TwitterException; // Custom Exception
import com.workintech.twiterdemo.repository.LikeRepository;
import com.workintech.twiterdemo.repository.TweetRepository;
import com.workintech.twiterdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; // Status kodları
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public void likeTweet(Long tweetId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TwitterException("Tweet bulunamadı", HttpStatus.NOT_FOUND));


        Optional<Like> existingLike = likeRepository.findByUserIdAndTweetId(user.getId(), tweet.getId());

        if(existingLike.isPresent()){

            throw new TwitterException("Bu tweet zaten beğenilmiş.", HttpStatus.BAD_REQUEST);
        }

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        likeRepository.save(like);
    }

    @Override
    public void dislikeTweet(Long tweetId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));


        Like existingLike = likeRepository.findByUserIdAndTweetId(user.getId(), tweetId)
                .orElseThrow(() -> new TwitterException("Beğeni bulunamadı, silinemiyor.", HttpStatus.NOT_FOUND));

        likeRepository.delete(existingLike);
    }
}