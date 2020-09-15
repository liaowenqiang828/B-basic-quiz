package com.thoughtworks.quizbackend.domian;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
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
    @Size(min = 1, max = 256, message = ErrorMessageConstants.TITLE_LENGTH_ERROR)
    private String title;
    @NotNull(message = ErrorMessageConstants.DESCRIPTION_EMPTY_ERROR)
    @Size(min = 1, max = 4096, message = ErrorMessageConstants.EDUCATION_DESCRIPTION_LENGTH_ERROR)
    private String description;
}
