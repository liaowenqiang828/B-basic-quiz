package com.thoughtworks.quizbackend;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.repository.UserRepository;
import com.thoughtworks.quizbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(123L)
                .name("Bryant")
                .age(42)
                .avatar("https://...")
                .description("play for lakes of LA")
                .build();
    }

    @Nested
    class GetUserById {
        @Nested
        class WhenUserIdExists {
            @Test
            public void should_return_user() {
                when(userRepository.findById(123L)).thenReturn(user);

                User userFound = userService.getUserById(123L);
                assertThat(userFound).isEqualTo(User.builder()
                        .id(123L)
                        .name("Bryant")
                        .age(42)
                        .avatar("https://...")
                        .description("play for lakes of LA")
                        .build());
            }
        }

        @Nested
        class WhenUserIdNotExists {
            @Test
            public void should_throw_id_not_matched_exception() {
                when(userRepository.findById(12345L)).thenReturn(null);

                assertThrows(IdNotMatchedException.class, () -> {
                    userService.getUserById(12345L);
                });
            }
        }
    }
}
