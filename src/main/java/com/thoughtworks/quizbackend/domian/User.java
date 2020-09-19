package com.thoughtworks.quizbackend.domian;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.constants.ValidateConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = ErrorMessageConstants.USER_NAME_EMPTY_ERROR)
    @Size(min = ValidateConstants.USERNAME_MIN_LENGTH,
            max = ValidateConstants.USERNAME_MAX_LENGTH,
            message = ErrorMessageConstants.USER_NAME_LENGTH_ERROR)
    private String name;
    @NotNull(message = ErrorMessageConstants.USER_AGE_EMPTY_ERROR)
    @Min(value = ValidateConstants.USER_MIN_AGE,
            message = ErrorMessageConstants.USER_AGE_VALUE_ERROR)
    private long age;
    @Size(min = ValidateConstants.USER_AVATAR_LINK_MIN_LENGTH,
            max = ValidateConstants.USER_AVATAR_LINK_MAX_LENGTH,
            message = ErrorMessageConstants.USER_AVATAR_LENGTH_ERROR)
    @NotNull(message = ErrorMessageConstants.AVATAR_LINK_EMPTY_ERROR)
    private String avatar;
    @Size(max = ValidateConstants.USER_DESCRIPTION_MAX_LENGTH,
            message = ErrorMessageConstants.USER_DESCRIPTION_LENGTH_ERROR)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Education> educationList;
}
