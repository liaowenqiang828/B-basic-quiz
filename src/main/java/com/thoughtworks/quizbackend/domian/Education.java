package com.thoughtworks.quizbackend.domian;

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
    @NotNull
    private long year;
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    @NotNull
    @Size(min = 1, max = 4096)
    private String description;
}
