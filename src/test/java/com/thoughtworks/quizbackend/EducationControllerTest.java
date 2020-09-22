package com.thoughtworks.quizbackend;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.controller.EducationController;
import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.service.EducationService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
public class EducationControllerTest {
    @MockBean
    private EducationService educationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Education> jacksonTester;

    private User user;
    private Education education;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(123L)
                .name("Bryant")
                .age(42)
                .avatar("https://...")
                .description("play for lakes of LA")
                .build();
        education = Education.builder()
                .id(123)
                .user(user)
                .year(1996)
                .description("draft in 13th pick in first round")
                .title("NBA Draft")
                .build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(educationService);
    }

    @Nested
    class GetEducationListByUserId {

        @Nested
        class WhenUserIdExists {
            @Test
            public void should_return_education_list_by_user_id() throws Exception {
                when(educationService.getEducationListByUserId(123L))
                        .thenReturn(Collections.singletonList(education));

                mockMvc.perform(get("/users/{id}/educations", 123L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].year", is(1996)));

                verify(educationService, times(1)).getEducationListByUserId(123L);
            }
        }

        @Nested
        class WhenUserIdNotExists {
            @Test
            public void should_throw_id_not_matched_exception() throws Exception {
                when(educationService.getEducationListByUserId(12345L))
                        .thenThrow(new IdNotMatchedException(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + 12345L));

                mockMvc.perform(get("/users/12345/educations"))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message", is(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + 12345)));
                verify(educationService).getEducationListByUserId(12345L);
            }
        }
    }

    @Nested
    class AddEducationByUserId {
        @Nested
        class WhenUserIdExists {
            @Test
            public void should_return_education_list_by_user_id() throws Exception {
                doNothing().when(educationService).addEducationByUserId(education, 123L);

                mockMvc.perform(post("/users/{id}/educations", 123L)
                        .content(jacksonTester.write(education).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());

                verify(educationService, times(1)).addEducationByUserId(education, 123L);
            }
        }
    }
}
