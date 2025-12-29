package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.entity.Comment;
import com.workintech.twiterdemo.entity.Tweet;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.exception.TwitterException; // Kendi exception sınıfımız
import com.workintech.twiterdemo.repository.CommentRepository;
import com.workintech.twiterdemo.repository.TweetRepository;
import com.workintech.twiterdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; // Status kodları için gerekli
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(Long tweetId, String content, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TwitterException("Tweet bulunamadı", HttpStatus.NOT_FOUND));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTweet(tweet);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new TwitterException("Yorum bulunamadı", HttpStatus.NOT_FOUND));


        boolean isCommentOwner = comment.getUser().getUsername().equals(username);
        boolean isTweetOwner = comment.getTweet().getUser().getUsername().equals(username);

        if (!isCommentOwner && !isTweetOwner) {

            throw new TwitterException("Bu yorumu silmeye yetkiniz yok.", HttpStatus.FORBIDDEN);
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findCommentsByTweetId(Long tweetId) {

        return commentRepository.findAllByTweetId(tweetId);
    }
}