package com.thoughtworks.quizbackend.controller;

import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class EducationController {

    private EducationService educationService;
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducationListByUserId(@PathVariable("userId") long userId) {
        return this.educationService.getEducationListByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/educations")
    public void addEducationByUserId(@PathVariable("userId") long userId, @RequestBody @Valid Education education) {
        this.educationService.addEducationByUserId(education, userId);
    }
}
