package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.dto.RegisterRequest;
import com.workintech.twiterdemo.dto.UserResponse;
import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.exception.TwitterException;
import com.workintech.twiterdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new TwitterException("Bu email zaten kullanımda.", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            throw new TwitterException("Bu kullanıcı adı zaten alınmış.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setUsername(registerRequest.getUsername());


        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User savedUser = userRepository.save(user);


        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new TwitterException("Kullanıcı bulunamadı: " + username, HttpStatus.NOT_FOUND));
    }
    @Override
    public List<User> search(String query) {
        if (query == null || query.isEmpty()) {
            return List.of();
        }
        return userRepository.searchUsers(query);
    }

}