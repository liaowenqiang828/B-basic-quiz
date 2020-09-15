package com.thoughtworks.quizbackend.domian;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    @NotNull(message = ErrorMessageConstants.USER_NAME_EMPTY_ERROR)
    @Size(min = 1, max = 128, message = ErrorMessageConstants.USER_NAME_LENGTH_ERROR)
    private String name;
    @NotNull(message = ErrorMessageConstants.USER_AGE_EMPTY_ERROR)
    @Min(value = 17, message = ErrorMessageConstants.USER_AGE_VALUE_ERROR)
    private long age;
    @Size(min = 8, max = 512, message = ErrorMessageConstants.USER_AVATAR_LENGTH_ERROR)
    @NotNull(message = ErrorMessageConstants.AVATAR_LINK_EMPTY_ERROR)
    private String avatar;
    @Size(max = 1024, message = ErrorMessageConstants.USER_DESCRIPTION_LENGTH_ERROR)
    private String description;
}
