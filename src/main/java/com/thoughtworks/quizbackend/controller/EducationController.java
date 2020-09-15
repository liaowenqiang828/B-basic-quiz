package com.thoughtworks.quizbackend.controller;

import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.service.EducationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EducationController {

    private EducationService educationService;
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducationListByUserId(@PathVariable("userId") long userId) {
        return this.educationService.getEducationListByUserId(userId);
    }
}
