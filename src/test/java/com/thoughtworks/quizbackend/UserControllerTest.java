package com.thoughtworks.quizbackend;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.controller.UserController;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<User> userJacksonTester;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(123L)
                .name("Bryant")
                .age(42)
                .avatar("https://...")
                .description("play for lakes of LA")
                .build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(userService);
    }

    @Nested
    class GetUserById {

        @Nested
        class WhenUserIdExists {

            @Test
            public void should_return_user_by_id_with_jsonPath() throws Exception {
                when(userService.getUserById(123L)).thenReturn(user);

                mockMvc.perform(get("/users/{id}", 123L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.name", is("Bryant")));

                verify(userService, times(1)).getUserById(123L);
            }

            @Test
            public void should_return_user_by_id_with_jackson_tester() throws Exception {
                when(userService.getUserById(123L)).thenReturn(user);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}", 123L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getContentAsString()).isEqualTo(
                        userJacksonTester.write(user).getJson()
                );
                verify(userService).getUserById(123L);
            }
        }

        @Nested
        class WhenUserIdNotExists {

            @Test
            public void should_throw_id_not_matched_exception_when_id_not_exists() throws Exception {
                when(userService.getUserById(122333L)).thenThrow(
                        new IdNotMatchedException(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + 122333L)
                );

                mockMvc.perform(get("/users/{id}", 122333L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message", is("Cannot find basic info for user with id 122333")));

                verify(userService).getUserById(122333L);

            }
        }
    }

    @Nested
    class CreateUser {

        @Nested
        class WhenUserIsValid {

            @Test
            public void should_return_user_when_add_user_success() throws Exception {
                when(userService.createUser(user)).thenReturn(user);

                mockMvc.perform(post("/users")
                        .content(userJacksonTester.write(user).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name", is("Bryant")));

                verify(userService, times(1)).createUser(user);
            }
        }
    }
}
