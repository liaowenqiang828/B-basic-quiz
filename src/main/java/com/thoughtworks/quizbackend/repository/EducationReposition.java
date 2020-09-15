package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EducationReposition {
    void save(Education education);

    List<Education> findByUserId(long userId);

    List<Education> findAll();
}
