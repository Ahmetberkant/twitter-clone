package com.workintech.twiterdemo.service;

import com.workintech.twiterdemo.entity.User;
import com.workintech.twiterdemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;

    @BeforeEach
    void setUp() {

        user1 = new User();
        user1.setId(1L);
        user1.setFullName("Ahmet Berkant");
        user1.setUsername("berkant");
        user1.setEmail("berkant@test.com");
    }

    @Test
    @DisplayName("Kullanıcı arama: İsim eşleşirse liste dönmeli")
    void search_ValidQuery_ReturnsUserList() {

        String query = "ber";

        when(userRepository.searchUsers("ber")).thenReturn(Arrays.asList(user1));


        List<User> results = userService.search(query);


        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("berkant", results.get(0).getUsername());
    }

    @Test
    @DisplayName("Kullanıcı arama: Query boş ise boş liste dönmeli")
    void search_EmptyQuery_ReturnsEmptyList() {

        List<User> results = userService.search("");


        assertEquals(0, results.size());
    }
}