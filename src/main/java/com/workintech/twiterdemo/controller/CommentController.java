package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.entity.Comment;
import com.workintech.twiterdemo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{tweetId}")
    public Comment createComment(@Valid @PathVariable Long tweetId, @RequestBody String content, Principal principal) {
        return commentService.createComment(tweetId, content, principal.getName());
    }


    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable Long id, Principal principal) {
        commentService.deleteComment(id, principal.getName());
    }


    @GetMapping("/tweet/{tweetId}")
    public List<Comment> getCommentsByTweet(@PathVariable Long tweetId) {
        return commentService.findCommentsByTweetId(tweetId);
    }
}