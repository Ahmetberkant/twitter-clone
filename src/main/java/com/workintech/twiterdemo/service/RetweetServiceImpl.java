package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.entity.Retweet;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.exception.TwitterException; // Custom Exception
import com.workintech.twiterdemo.repository.RetweetRepository;
import com.workintech.twiterdemo.repository.TweetRepository;
import com.workintech.twiterdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; // Status kodları
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public void retweet(Long tweetId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TwitterException("Tweet bulunamadı", HttpStatus.NOT_FOUND));


        if(retweetRepository.findByUserIdAndOriginalTweetId(user.getId(), tweet.getId()).isPresent()){

            throw new TwitterException("Bu tweeti zaten retweetlediniz.", HttpStatus.BAD_REQUEST);
        }

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setOriginalTweet(tweet);
        retweetRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(Long tweetId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));


        Retweet retweet = retweetRepository.findByUserIdAndOriginalTweetId(user.getId(), tweetId)
                .orElseThrow(() -> new TwitterException("Retweet bulunamadı, silinemiyor.", HttpStatus.NOT_FOUND));

        retweetRepository.delete(retweet);
    }
}