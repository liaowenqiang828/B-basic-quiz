package com.thoughtworks.quizbackend;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.repository.EducationReposition;
import com.thoughtworks.quizbackend.repository.UserRepository;
import com.thoughtworks.quizbackend.service.EducationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
    private EducationService educationService;

    @Mock
    private UserRepository userRepository;
    @Mock
    EducationReposition educationReposition;
    private Education education;
    private User user;

    @BeforeEach
    void setUp() {
        educationService = new EducationService(educationReposition, userRepository);
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

    @Nested
    class GetEducationListByUserId {
        @Nested
        class WhenUserIdExist {
            @Test
            public void should_return_education_list() {
                when(userRepository.findById(123L))
                        .thenReturn(user);
                when(educationReposition.findAllByUser(user))
                        .thenReturn(Collections.singletonList(education));

                List<Education> listFound = educationService.getEducationListByUserId(123L);
                assertThat(listFound.size()).isEqualTo(1);
                assertThat(listFound.get(0).getYear()).isEqualTo(1996);
            }
        }
    }

    @Nested
    class AddEducationByUserId {
        @Nested
        class WhenUserIdExists {
            @Test
            public void should_add_education_success() {
                when(userRepository.findById(123L)).thenReturn(user);

                educationService.addEducationByUserId(education, 123L);

                verify(userRepository, times(1)).findById(123L);
                verify(educationReposition, times(1)).save(education);
            }
        }
    }

    @Nested
    class IsUserExist {
        @Test
        public void should_throw_id_not_matched_exception_when_user_null() {
            IdNotMatchedException exception = assertThrows(
                    IdNotMatchedException.class,
                    () -> educationService.isUserExist(null, 12345L)
            );

            assertTrue(exception.getMessage().contains(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + 12345L));
        }
    }

}
