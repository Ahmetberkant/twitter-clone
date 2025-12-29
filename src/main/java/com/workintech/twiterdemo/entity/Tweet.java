package com.workintech.twiterdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // <--- IMPORT EKLENDİ
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Tweet içeriği boş olamaz")
    @Size(max = 280, message = "Tweet en fazla 280 karakter olabilir")
    @Column(name = "content", length = 280, nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Like> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "originalTweet", cascade = CascadeType.ALL)
    private List<Retweet> retweets;
}