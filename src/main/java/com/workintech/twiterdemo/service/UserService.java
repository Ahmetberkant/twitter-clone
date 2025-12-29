package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.dto.RegisterRequest;
import com.workintech.twiterdemo.dto.UserResponse;
import com.workintech.twiterdemo.entity.User;

import java.util.List;

public interface UserService {
    UserResponse register(RegisterRequest registerRequest);
    User findByUsername(String username);
    List<User> search(String query);
}