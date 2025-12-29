package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.dto.TweetRequest;
import com.workintech.twiterdemo.dto.TweetResponse;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.mapper.TweetMapper;
import com.workintech.twiterdemo.repository.TweetRepository;
import com.workintech.twiterdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public TweetResponse save(TweetRequest tweetRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        user.addTweet(tweet);

        Tweet savedTweet = tweetRepository.save(tweet);
        return mapToResponse(savedTweet);
    }

    @Override
    public List<TweetResponse> findAll() {
        return tweetRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TweetResponse> findAllByUserId(Long userId) {
        return tweetRepository.findAllByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Tweet findById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
    }

    @Override
    public void delete(Long tweetId, String username) {
        Tweet tweet = findById(tweetId);


        if (!tweet.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Bu tweeti silmeye yetkiniz yok.");
        }
        tweetRepository.delete(tweet);
    }

    private TweetResponse mapToResponse(Tweet tweet) {
        TweetResponse response = new TweetResponse();
        response.setId(tweet.getId());
        response.setContent(tweet.getContent());
        response.setUsername(tweet.getUser().getUsername());
        response.setFullName(tweet.getUser().getFullName());
        response.setCreatedAt(tweet.getCreatedAt());
        return response;
    }
    @Override
    public TweetResponse update(Long id, TweetRequest tweetRequest) {
        Tweet existingTweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı!"));

        existingTweet.setContent(tweetRequest.getContent());


        Tweet updatedTweet = tweetRepository.save(existingTweet);


        return TweetMapper.toResponse(updatedTweet);

    }

}