package com.workintech.twiterdemo.controller;

import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // URL: http://localhost:3000/user/search?query=berkant
    @GetMapping("/search")
    public List<User> search(@RequestParam String query) {
        return userService.search(query);
    }
}