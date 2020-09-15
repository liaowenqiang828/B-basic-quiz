package com.thoughtworks.quizbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private long userId;
    private long year;
    private String title;
    private String description;
}
