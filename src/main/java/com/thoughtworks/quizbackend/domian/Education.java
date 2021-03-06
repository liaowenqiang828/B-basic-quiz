package com.thoughtworks.quizbackend.domian;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.constants.ValidateConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private long userId;
    @NotNull(message = ErrorMessageConstants.YEAR_EMPTY_ERROR)
    private long year;
    @NotNull(message = ErrorMessageConstants.TITLE_EMPTY_ERROR)
    @Size(min = ValidateConstants.TITLE_MIN_LENGTH,
            max = ValidateConstants.TITLE_MAX_LENGTH,
            message = ErrorMessageConstants.TITLE_LENGTH_ERROR)
    private String title;
    @NotNull(message = ErrorMessageConstants.DESCRIPTION_EMPTY_ERROR)
    @Size(min = ValidateConstants.EDUCATION_DESCRIPTION_MIN_LENGTH,
            max = ValidateConstants.EDUCATION_DESCRIPTION_MAX_LENGTH,
            message = ErrorMessageConstants.EDUCATION_DESCRIPTION_LENGTH_ERROR)
    private String description;
}
