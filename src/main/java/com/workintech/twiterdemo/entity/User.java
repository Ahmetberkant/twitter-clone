package com.workintech.twiterdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // <--- BU IMPORT ÖNEMLİ
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    @Size(min = 2, max = 50)
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Kullanıcı adı zorunludur")
    @Size(min = 3, max = 20)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email zorunludur")
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Şifre zorunludur")
    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;



    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweet> retweets;

    public void addTweet(Tweet tweet) {
        if (tweets == null) {
            tweets = new ArrayList<>();
        }
        tweets.add(tweet);
        tweet.setUser(this);
    }
    @PreUpdate
    public void onUpdate() {
        this.createdAt = LocalDateTime.now();
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}