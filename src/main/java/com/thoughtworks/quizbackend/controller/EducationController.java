package com.thoughtworks.quizbackend.controller;

import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@CrossOrigin
public class EducationController {

    private EducationService educationService;
    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducationListByUserId(@PathVariable("userId") long userId) {
        return this.educationService.getEducationListByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/educations")
    @Transient
    public void addEducationByUserId(@PathVariable("userId") long userId, @RequestBody @Valid Education education) {
        this.educationService.addEducationByUserId(education, userId);
    }
}
